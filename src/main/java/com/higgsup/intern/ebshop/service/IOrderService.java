package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.OrderDTO;

public interface IOrderService {
    void createOrder(OrderDTO orderDTO);
}
