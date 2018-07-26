package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.OrderExportDTO;

public interface IOrderService {
    OrderExportDTO exportById(Long id);
}
