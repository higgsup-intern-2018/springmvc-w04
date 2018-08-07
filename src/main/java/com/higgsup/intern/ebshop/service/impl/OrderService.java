package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.OrderDetails;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import com.higgsup.intern.ebshop.jpa.repo.*;
import com.higgsup.intern.ebshop.service.IOrderService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.ItemDTO;
import com.higgsup.intern.ebshop.dto.OrderDTO;
import com.higgsup.intern.ebshop.exception.NotEnoughResourceException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository iOrderRepository;
    private final IEbookRepository iEbookRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ICustomerRepository iCustomerRepository;
    private final MapperFacade mapper;

    public OrderService(IOrderRepository iOrderRepository, IEbookRepository iEbookRepository, OrderDetailsRepository orderDetailsRepository, ICustomerRepository iCustomerRepository, MapperFacade mapper) {
        this.iOrderRepository = iOrderRepository;
        this.iEbookRepository = iEbookRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.iCustomerRepository = iCustomerRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public OrderExportDTO exportById(Long id) {
        if (iOrderRepository.findOne(id)== null) {
            throw new ResourceNotFoundException(String.format("Order with id = %d does not exist!", id));
        }else {
            Customer customer = iOrderRepository.getCustomerByOrdersId(id);
            List<Ebook> ebooks = iOrderRepository.getEbookByOrdersId(id);
            List<ItemInfoDTO> itemInfoDTOs = new ArrayList<>();

            for (Ebook ebook : ebooks) {
                ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
                itemInfoDTO.setAuthorFirstName(customer.getFirstName());
                itemInfoDTO.setAuthorLastName(customer.getLastName());
                itemInfoDTO.setPublisherName(ebook.getPublisher().getName());
                itemInfoDTO.setPrice(ebook.getPrice());
                itemInfoDTO.setCopiesSold(iOrderRepository.getQuantityByEbookId(ebook.getId(), id));
                itemInfoDTOs.add(itemInfoDTO);
            }

            OrderExportDTO orderExportDTO = new OrderExportDTO();
            orderExportDTO.setId(id);
            orderExportDTO.setItemList(itemInfoDTOs);
            orderExportDTO.setCustomerFirstName(customer.getFirstName());
            orderExportDTO.setCustomerLastName(customer.getLastName());
            orderExportDTO.setEmail(customer.getEmail());
            orderExportDTO.setPhone(customer.getPhone());
            orderExportDTO.setTotalPrice(iOrderRepository.totalPrice(id));

            return orderExportDTO;
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void createOrder(OrderDTO orderDTO) {
        verifyOrder(orderDTO);
        Orders orders = new Orders();
        Date date = new Date();
        orders.setCreatedDate(date);
        Customer customer = new Customer();
        String email = orderDTO.getEmail();
        if (iCustomerRepository.findByEmail(email) == null) {
            customer.setFirstName(orderDTO.getFirstName());
            customer.setLastName(orderDTO.getLastName());
            customer.setEmail(orderDTO.getEmail());
            customer.setPhone(orderDTO.getPhone());
            customer.setAddress(orderDTO.getAddress());
            iCustomerRepository.save(customer);
        }else{
            customer = iCustomerRepository.findByEmail(email);
        }
        orders.setCustomer(customer);
        iOrderRepository.save(orders);

        for (ItemDTO itemDTO : orderDTO.getItemList()) {
            OrderDetails orderDetails = new OrderDetails();
            Ebook ebook = iEbookRepository.findByIsbn(itemDTO.getIsbn());
            ebook.setQuantity(ebook.getQuantity() - itemDTO.getQuantity());
            iEbookRepository.save(ebook);

            orderDetails.setOrders(orders);
            orderDetails.setEbook(ebook);
            orderDetails.setQuantity(itemDTO.getQuantity());
            orderDetailsRepository.save(orderDetails);
        }

    }

    private void verifyOrder(OrderDTO orderDTO) {
        for (ItemDTO itemDTO : orderDTO.getItemList()) {
            Ebook ebook = iEbookRepository.findByIsbn(itemDTO.getIsbn());
            if (ebook == null || ebook.getDeleted() == true) {
                throw new ServiceException(String.format("Book with isbn = %s does not exist!", itemDTO.getIsbn()));
            }
            if (ebook.getQuantity() < itemDTO.getQuantity()) {
                throw new NotEnoughResourceException(String.format("Book with isbn = %s does not have enough quantity!", itemDTO.getIsbn()));
            }
        }
    }
}
