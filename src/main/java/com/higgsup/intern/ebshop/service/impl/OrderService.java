package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.jpa.repo.*;
import com.higgsup.intern.ebshop.service.IOrderService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.ItemDTO;
import com.higgsup.intern.ebshop.dto.OrderDTO;
import com.higgsup.intern.ebshop.exception.NotEnoughResourceException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.OrderDetails;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService implements IOrderService {

    private final EbookRepository ebookRepository;
    private final OrdersRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerRepository customerRepository;
    private final ICustomerRepository iCustomerRepository;
    private final IOrderRepository iOrderRepository;
    private final MapperFacade mapper;

    public OrderService(EbookRepository ebookRepository, OrdersRepository orderRepository, OrderDetailsRepository orderDetailsRepository, CustomerRepository customerRepository,
                        ICustomerRepository iCustomerRepository, IOrderRepository iOrderRepository, MapperFacade mapper) {
        this.ebookRepository = ebookRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerRepository = customerRepository;
        this.iCustomerRepository = iCustomerRepository;
        this.iOrderRepository = iOrderRepository;
        this.mapper = mapper;
    }


    @Override
    public List<EbookOrderDTO> getEbookOrderList(Long id) {
        List<EbookOrderDTO> ebookOrders = iOrderRepository.findByOrderId(id);

        return ebookOrders;

    }

    @Override
    public OrderExportDTO exportById(Long id) {
        OrderExportDTO orderExport = iOrderRepository.exportOrder(id);
        if (orderExport.getId() == 0) {
            throw new ResourceNotFoundException(String.format("Order with id = %d does not exist!", id));
        }
        List<EbookOrderDTO> ebookOrderList = getEbookOrderList(id);
        orderExport.setItemList(ebookOrderList);
        return orderExport;
    }


    @Override
    public void createOrder(OrderDTO orderDTO) {
        verifyOrder(orderDTO);
        Orders orders = new Orders();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.setCreatedDate(date);

        Long customerId = iCustomerRepository.getId(orderDTO.getEmail());
        if (customerId == null) {
            Customer customer = new Customer();
            customer.setFirstName(orderDTO.getFirstName());
            customer.setLastName(orderDTO.getLastName());
            customer.setEmail(orderDTO.getEmail());
            customer.setPhone(orderDTO.getPhone());
            customer.setAddress(orderDTO.getAddress());
            customerId = iCustomerRepository.getId(orderDTO.getEmail());
            customer.setId(customerId);
            customerRepository.save(customer);
        }
        orderRepository.save(orders);

        Orders order = orderRepository.findOne(iOrderRepository.getId(dateFormat.format(date)));
        for (ItemDTO itemDTO : orderDTO.getItemList()) {
            OrderDetails orderDetails = new OrderDetails();
            Ebook ebook = ebookRepository.findOne(Long.valueOf(itemDTO.getIsbn()));
            ebook.setQuantity(ebook.getQuantity() - itemDTO.getQuantity());
            ebookRepository.save(ebook);
            orderDetails.setOrders(order);
            orderDetails.setEbook(ebook);
            orderDetails.setQuantity(itemDTO.getQuantity());
            orderDetailsRepository.save(orderDetails);
        }
    }


    public void verifyOrder(OrderDTO orderDTO) {
        for (ItemDTO itemDTO : orderDTO.getItemList()) {
            Ebook ebook = ebookRepository.findOne(Long.valueOf(itemDTO.getIsbn()));
            if (ebook == null || ebook.getDeleted() == true) {
                throw new ServiceException(String.format("Book with isbn = %s does not exist!", itemDTO.getIsbn()));
            }
            if (ebook.getQuantity() < itemDTO.getQuantity()) {
                throw new NotEnoughResourceException(String.format("Book with isbn = %s does not have enough quantity!", itemDTO.getIsbn()));
            }
        }
    }
}
