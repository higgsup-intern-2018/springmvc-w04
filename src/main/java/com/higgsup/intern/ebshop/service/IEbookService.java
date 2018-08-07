package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;

public interface IEbookService {
    EbookDTO findById(Long id);

    GenericResponseDTO create(EbookDTO ebookDTO);

    void update(EbookDTO ebookDTO);

    void delete(Long id);
}
