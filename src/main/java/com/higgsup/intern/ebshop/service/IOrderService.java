package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.OrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;

public interface IOrderService {
    void create(OrderDTO orderDTO);
    OrderExportDTO exportById(Long id);
}
