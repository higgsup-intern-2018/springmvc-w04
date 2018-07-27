package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> findTop5BestBuyCustomers() {
        return null;
    }

    @Override
    public List<Customer> findTop5HighestOrderPriceCustomers() {
        return null;
    }
}
