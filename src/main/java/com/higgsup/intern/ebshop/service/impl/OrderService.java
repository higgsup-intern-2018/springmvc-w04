package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookInfoDTO;
import com.higgsup.intern.ebshop.dto.ItemDTO;
import com.higgsup.intern.ebshop.dto.OrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.exception.NotEnoughResourceException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.OrderDetails;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import com.higgsup.intern.ebshop.jpa.repo.CustomerRepository;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.OrderDetailsRepository;
import com.higgsup.intern.ebshop.jpa.repo.OrdersRepository;
import com.higgsup.intern.ebshop.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final EbookRepository ebookRepository;
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderService(EbookRepository ebookRepository, CustomerRepository customerRepository,
                        OrdersRepository ordersRepository, OrderDetailsRepository orderDetailsRepository) {
        this.ebookRepository = ebookRepository;
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public void create(OrderDTO orderDTO) {
        verifyOrder(orderDTO);
        Orders orders = new Orders();
        Date date = new Date();
        orders.setCreatedDate(date);

        String email = orderDTO.getEmail();
        Customer customer = new Customer();
        if (customerRepository.findByEmail(email) == null) {
            customer.setFirstName(orderDTO.getFirstName());
            customer.setLastName(orderDTO.getLastName());
            customer.setEmail(orderDTO.getEmail());
            customer.setPhone(orderDTO.getPhone());
            customer.setAddress(orderDTO.getAddress());
            customerRepository.save(customer);
        }else {
            customer = customerRepository.findByEmail(email);
        }
        orders.setCustomer(customer);
        ordersRepository.save(orders);

        for (ItemDTO itemDTO : orderDTO.getItemList()) {
            OrderDetails orderDetails = new OrderDetails();
            Ebook ebook = ebookRepository.findByIsbn(itemDTO.getIsbn());
            ebook.setQuantity(ebook.getQuantity() - itemDTO.getQuantity());
            ebookRepository.save(ebook);
            orderDetails.setOrders(orders);
            orderDetails.setEbook(ebook);
            orderDetails.setQuantity(itemDTO.getQuantity());
            orderDetailsRepository.save(orderDetails);
        }
    }

    @Override
    public OrderExportDTO exportById(Long id) {
        Customer customer = ordersRepository.getCustomerByOrdersId(id);
        List<Ebook> ebooks = ordersRepository.getEbookByOrdersId(id);
        Double totalPrice = ordersRepository.totalPrice(id);
        List<EbookInfoDTO> ebookInfoDTOs = new ArrayList<>();
        for(Ebook e: ebooks)
        {
            EbookInfoDTO ebookInfoDTO = new EbookInfoDTO();
            ebookInfoDTO.setTitle(e.getTitle());
            ebookInfoDTO.setAuthorFirstName(e.getAuthor().getFirstName());
            ebookInfoDTO.setAuthorLastName(e.getAuthor().getLastName());
            ebookInfoDTO.setPublisher(e.getPublisher().getName());
            ebookInfoDTO.setPrice(e.getPrice());
            ebookInfoDTO.setCopiesSold(ordersRepository.getQuantityByEbookId(e.getId()));
            ebookInfoDTOs.add(ebookInfoDTO);
        }

        OrderExportDTO orderExportDTO = new OrderExportDTO();
        orderExportDTO.setCustomerFirstName(customer.getFirstName());
        orderExportDTO.setCustomerLastName(customer.getLastName());
        orderExportDTO.setEmail(customer.getEmail());
        orderExportDTO.setPhone(customer.getPhone());
        orderExportDTO.setItemList(ebookInfoDTOs);
        orderExportDTO.setTotalPrice(totalPrice);
        return orderExportDTO;
    }

    private void verifyOrder(OrderDTO orderDTO) {
        for (ItemDTO itemDTO : orderDTO.getItemList()) {
            Ebook ebook = ebookRepository.findByIsbn(itemDTO.getIsbn());
            if (ebook == null || ebook.getDeleted()) {
                throw new ServiceException(String.format("Book with isbn = %s does not exist!", itemDTO.getIsbn()));
            }
            if (ebook.getQuantity() < itemDTO.getQuantity()) {
                throw new NotEnoughResourceException(String.format("Book with isbn = %s does not have enough quantity!", itemDTO.getIsbn()));
            }
        }
    }
}
