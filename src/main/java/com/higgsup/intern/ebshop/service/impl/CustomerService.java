package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jpa.repo.CustomerRepository;
import com.higgsup.intern.ebshop.service.ICustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getTop5MostBuying() {

        return null;
    }

    @Override
    public List<CustomerDTO> getTop5HighestPrice() {
        return null;
    }
}
