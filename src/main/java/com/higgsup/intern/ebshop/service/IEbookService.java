package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.EbookDTO;

public interface IEbookService {
    EbookDTO findById(Long id);
    void create(EbookDTO ebookDTO);
    void update(EbookDTO ebookDTO);
    void delete(Long id);
}