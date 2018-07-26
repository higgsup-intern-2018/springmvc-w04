package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService {
    private final PublisherDAO publisherDAO;
    private final MapperFacade mapper;

    public PublisherService(PublisherDAO publisherDAO, MapperFacade mapper)
    {
        this.publisherDAO = publisherDAO;
        this.mapper = mapper;
    }

    @Override
    public PublisherDTO findById(Long id) {
        Publisher publisher = publisherDAO.findById(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(String.format("Publisher with id = %d does not exist!", id));
        }
        return mapper.map(publisher, PublisherDTO.class);
    }


    @Override
    public void delete(Long id) {
        if (publisherDAO.findById(id) == null) {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
        if(publisherDAO.countBook(id) > 0)
        {
            throw new ServiceException(String.format("Publisher with id = %d still have book in it!", id));
        }
        publisherDAO.delete(id);
    }
}
