package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Orders;

public interface OrderDAO {
    void createOrder(Orders orders);
    Long getId(String date);
}
