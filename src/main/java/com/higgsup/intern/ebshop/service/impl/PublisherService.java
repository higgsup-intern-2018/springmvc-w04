package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.PublisherDTO;
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
    public void create(PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherDAO.create(publisher);
    }
}
