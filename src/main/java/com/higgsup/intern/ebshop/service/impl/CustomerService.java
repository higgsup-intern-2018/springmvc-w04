package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.repo.CustomerRepository;
import com.higgsup.intern.ebshop.jpa.repo.ICustomerRepository;
import com.higgsup.intern.ebshop.service.ICustomerService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final MapperFacade mapper;
    private final ICustomerRepository iCustomerRepository;

    public CustomerService(CustomerRepository customerRepository, MapperFacade mapper, ICustomerRepository iCustomerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.iCustomerRepository = iCustomerRepository;
    }


    @Override
    public CustomerListDTO findTop5HighestOrderPriceCustomers() {
       List<CustomerDTO> customers = iCustomerRepository.findTop5HighestOrderPriceCustomers();

        List<CustomerDTO> customerDTOList = mapper.mapAsList(customers, CustomerDTO.class);
        CustomerListDTO customerListDTO = new CustomerListDTO();

        customerListDTO.setCustomerDTOs(customerDTOList);

        return customerListDTO;


    }

    @Override
    public CustomerListDTO findTop5BestBuyCustomers() {
      List<Customer> customers = iCustomerRepository.findTop5BestBuyCustomers();

        List<CustomerDTO> customerDTOs = mapper.mapAsList(customers, CustomerDTO.class);
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.setCustomerDTOs(customerDTOs);
        return customerListDTO;

    }

}
