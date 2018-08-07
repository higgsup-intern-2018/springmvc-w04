package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.PublisherRepository;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService implements IPublisherService {
    private final PublisherRepository publisherRepository;
    private final EbookRepository ebookRepository;
    private final MapperFacade mapper;

    public PublisherService(PublisherRepository publisherRepository, EbookRepository ebookRepository, MapperFacade mapper) {
        this.publisherRepository = publisherRepository;
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PublisherDTO publisherDTO) {
        Long id = publisherDTO.getId();
        if(publisherRepository.findOne(id) == null)
        {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if(publisherRepository.findOne(id) == null)
        {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
        if(publisherRepository.countEbooksOfPublisher(id) > 0)
        {
            throw new ServiceException(String.format("Publisher with id = %d still has books!", id));
        }
        publisherRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PublisherDTO findById(Long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if(publisher == null)
        {
            throw new ResourceNotFoundException(String.format("Publisher with id = %d does not exist!", id));
        }
        PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
        publisherDTO.setCountOfBooks(publisherRepository.countEbooksOfPublisher(id));
        List<Ebook> ebooks = publisherRepository.findTop5BestSelling(id, new PageRequest(0, 5));
        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);
        for(EbookDTO ebookDTO: ebookDTOs)
        {
            Author author = ebookRepository.findAuthorByEbookId(ebookDTO.getId());
            AuthorDTO authorDTO = mapper.map(author, AuthorDTO.class);
            ebookDTO.setAuthorDTO(authorDTO);
        }
        publisherDTO.setTop5BestSellings(ebookDTOs);
        return publisherDTO;
    }
}
