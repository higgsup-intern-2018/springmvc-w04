package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;

import java.util.List;
import com.higgsup.intern.ebshop.jdbc.model.Orders;

public interface OrderDAO {
    OrderExportDTO exportOrder(Long id);
    List<EbookOrderDTO> findByOrderId(Long id);

    void createOrder(Orders orders);
    Long getId(String date);
}
