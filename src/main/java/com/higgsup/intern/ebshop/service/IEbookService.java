package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;

import java.util.List;

public interface IEbookService {
    void update(EbookDTO ebookDTO);
    EbookDTO findById(Long id);
   GenericResponseDTO create(EbookDTO ebookDTO);
    void delete(Long id);
   EbookOrderListDTO top10BestSellers();
}
