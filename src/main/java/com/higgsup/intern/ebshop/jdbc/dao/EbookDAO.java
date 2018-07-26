package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;

import java.util.List;

public interface EbookDAO {
    List<EbookOrderDTO> top10BestSeller();
}
