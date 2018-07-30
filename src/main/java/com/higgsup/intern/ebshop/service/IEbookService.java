package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.jdbc.model.Search;

import java.util.List;

public interface IEbookService {
    void update(EbookDTO ebookDTO);
    EbookDTO findById(Long id);
    GenericResponseDTO create(EbookDTO ebookDTO);
    void delete(Long id);
    EbookOrderListDTO top10BestSellers();
    EbookListDTO searchEbook(String name, Long authorId, Long publisherId, Double priceFrom, Double priceTo, String isbn);
}
