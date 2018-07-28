package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    Long getId(String email);
}
