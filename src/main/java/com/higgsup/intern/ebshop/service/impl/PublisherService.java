package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService implements IPublisherService{
    private final PublisherDAO publisherDAO;
    private final MapperFacade mapper;

    public PublisherService(PublisherDAO publisherDAO, MapperFacade mapper) {
        this.publisherDAO = publisherDAO;
        this.mapper = mapper;
    }

    @Override
    public PublisherDTO findById(Long id) {
        return null;
    }

    @Override
    public PublisherListDTO top5BestSellingPublisher() {
        List<Publisher> publishers = publisherDAO.findTop5BestSellingPublishers();

        List<PublisherDTO> publisherDTOS = mapper.mapAsList(publishers, PublisherDTO.class);

        PublisherListDTO publisherListDTO = new PublisherListDTO();
        publisherListDTO.setPublisherDTOList(publisherDTOS);

        return publisherListDTO;
    }
}
