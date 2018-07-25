package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@Service
public class EbookService implements IEbookService {
    private final EbookDAO ebookDAO;
    private final MapperFacade mapper;

    public EbookService(EbookDAO ebookDAO, MapperFacade mapper) {
        this.ebookDAO = ebookDAO;
        this.mapper = mapper;
    }

    @Override
    public EbookDTO findById(Long id) {
        return null;
    }

    @Override
    public void create(EbookDTO ebookDTO) {
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        ebookDAO.create(ebook);
    }

    @Override
    public void update(EbookDTO ebookDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
