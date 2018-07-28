package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
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
    public void delete(Long id) {
        if (ebookDAO.findById(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }
        ebookDAO.delete(id);
    }
}
