package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import com.higgsup.intern.ebshop.service.ICustomerService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerDAO customerDAO;
    private final MapperFacade mapper;

    public CustomerService(CustomerDAO customerDAO, MapperFacade mapper) {
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }

    @Override
    public CustomerListDTO findTop5HighestOrderPriceCustomers() {
        List<CustomerDTO> customers = customerDAO.findTop5HighestOrderPriceCustomers();

        List<CustomerDTO> customerDTOList = mapper.mapAsList(customers,CustomerDTO.class);
        CustomerListDTO customerListDTO = new CustomerListDTO();

        customerListDTO.setCustomerDTOs(customerDTOList);

        return customerListDTO;
    }
    @Override
    public CustomerListDTO findTop5BestBuyCustomers() {
        List<Customer> customers = customerDAO.findTop5BestBuyCustomers();

        List<CustomerDTO> customerDTOs = mapper.mapAsList(customers, CustomerDTO.class);
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.setCustomerDTOs(customerDTOs);
        return customerListDTO;
    }
}
