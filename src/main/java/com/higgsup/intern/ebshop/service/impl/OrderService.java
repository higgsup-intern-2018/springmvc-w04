package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.ItemDTO;
import com.higgsup.intern.ebshop.dto.OrderDTO;
import com.higgsup.intern.ebshop.exception.NotEnoughResourceException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.dao.OrderDAO;
import com.higgsup.intern.ebshop.jdbc.dao.OrderDetailsDAO;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.OrderDetails;
import com.higgsup.intern.ebshop.jdbc.model.Orders;
import com.higgsup.intern.ebshop.service.IOrderService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService implements IOrderService {
    private final EbookDAO ebookDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final CustomerDAO customerDAO;
    private final MapperFacade mapper;

    public OrderService(EbookDAO ebookDAO, OrderDAO orderDAO, OrderDetailsDAO orderDetailsDAO, CustomerDAO customerDAO, MapperFacade mapper) {
        this.ebookDAO = ebookDAO;
        this.orderDAO = orderDAO;
        this.orderDetailsDAO = orderDetailsDAO;
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }

    @Override
    public void createOrder(OrderDTO orderDTO){
        verifyOrder(orderDTO);
        Orders orders = new Orders();
        Date date = new Date();
        orders.setCreatedDate(date);

        Long customerId = customerDAO.getId(orderDTO.getEmail());
        if(customerId == null)
        {
            Customer customer = new Customer();
            customer.setFirstName(orderDTO.getFirstName());
            customer.setLastName(orderDTO.getLastName());
            customer.setEmail(orderDTO.getEmail());
            customer.setPhone(orderDTO.getPhone());
            customer.setAddress(orderDTO.getAddress());
            customerDAO.createCustomer(customer);
            customerId = customerDAO.getId(orderDTO.getEmail());
        }
        orders.setCustomerId(customerId);

        for(ItemDTO itemDTO : orderDTO.getItemList())
        {
            OrderDetails orderDetails = new OrderDetails();
            Ebook ebook = ebookDAO.findByIsbn(itemDTO.getIsbn());
            ebook.setQuantity(ebook.getQuantity() - itemDTO.getQuantity());
            ebookDAO.update(ebook);
            orderDetails.setEbookId(ebook.getId());
            orderDetails.setQuantity(itemDTO.getQuantity());
            orderDetailsDAO.createOrderDetails(orderDetails);
        }
        orderDAO.createOrder(orders);
    }

    public void verifyOrder(OrderDTO orderDTO)
    {
        for(ItemDTO itemDTO : orderDTO.getItemList())
        {
            Ebook ebook = ebookDAO.findByIsbn(itemDTO.getIsbn());
            if(ebook == null || ebook.getDeleted() == true)
            {
                throw new ServiceException(String.format("Book with isbn = %s does not exist!", itemDTO.getIsbn()));
            }
            if(ebook.getQuantity() < itemDTO.getQuantity())
            {
                throw new NotEnoughResourceException(String.format("Book with isbn = %s does not have enough quantity!", itemDTO.getIsbn()));
            }
        }
    }
}
