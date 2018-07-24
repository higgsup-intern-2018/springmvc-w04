package com.higgsup.intern.ebshop.jdbc.dao;

import org.springframework.core.annotation.Order;

public interface OrderDAO {
    Order exportOrder(Long id);
}
