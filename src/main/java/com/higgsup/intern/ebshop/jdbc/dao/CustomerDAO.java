package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jdbc.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    Long getId(String email);
    List<Customer> findTop5BestBuyCustomers();
    List<CustomerDTO> findTop5HighestOrderPriceCustomers();
    Integer countBook(Long id);
}
