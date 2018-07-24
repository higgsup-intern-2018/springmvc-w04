package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findTop5BestBuyCustomers();
    List<Customer> findTop5HighestOrderPriceCustomers();
}
