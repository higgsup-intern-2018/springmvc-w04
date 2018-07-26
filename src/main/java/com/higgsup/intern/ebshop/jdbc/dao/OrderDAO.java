package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;

import java.util.List;

public interface OrderDAO {
    OrderExportDTO exportOrder(Long id);
    List<EbookOrderDTO> findByOrderId(Long id);
}
