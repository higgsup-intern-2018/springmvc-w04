package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.repo.ICustomerRepository;
import com.higgsup.intern.ebshop.service.ICustomerService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository iCustomerRepository;
    private final MapperFacade mapper;

    public CustomerService(ICustomerRepository iCustomerRepository, MapperFacade mapper) {
        this.iCustomerRepository = iCustomerRepository;
        this.mapper = mapper;
    }


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public CustomerListDTO findTop5HighestOrderPriceCustomers() {
        List<Customer> customers = iCustomerRepository.findTop5HighestOrderPriceCustomers();
        List<CustomerDTO> customerDTOList = mapper.mapAsList(customers, CustomerDTO.class);
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.setCustomerDTOs(customerDTOList);
        return customerListDTO;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public CustomerListDTO findTop5BestBuyCustomers() {
        List<Customer> customers = iCustomerRepository.findTop5BestBuyCustomers();
        List<CustomerDTO> customerDTOs = mapper.mapAsList(customers, CustomerDTO.class);
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.setCustomerDTOs(customerDTOs);
        return customerListDTO;
    }
}
