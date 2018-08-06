package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.OrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderDTO orderDTO);
    OrderExportDTO exportById(Long id);
    List<EbookOrderDTO> getEbookOrderList(Long id);
}
