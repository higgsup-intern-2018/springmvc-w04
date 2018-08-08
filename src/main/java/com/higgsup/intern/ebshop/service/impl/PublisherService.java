package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.IPublisherRepository;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService implements IPublisherService {
    private final IPublisherRepository publisherRepository;
    private final IEbookRepository ebookRepository;
    private final MapperFacade mapper;

    public PublisherService(IPublisherRepository publisherRepository, IEbookRepository ebookRepository, MapperFacade mapper) {
        this.publisherRepository = publisherRepository;
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO,Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(PublisherDTO publisherDTO) {
        Long id = publisherDTO.getId();
        if (publisherRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        if (publisherRepository.findOne(id) == null){
            throw new ServiceException(String.format("The publisher with id = %d has not existed!", id));
        }
        else if (publisherRepository.countEbooksOfPublisher(id) == null){
            publisherRepository.delete(id);
        } else {
            throw new ServiceException(String.format("Not deleted! Because the publisher with id = %d has some ebooks!", id));
        }
    }

    @Override
    public PublisherDTO findPublisherById(Long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(String.format("Publisher with id = %d does not exist!", id));
        }
        List<Ebook> ebooks = publisherRepository.top5BookOfPublisher(id, new PageRequest(0,5));

        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);
        for (EbookDTO item : ebookDTOs) {
            Author authors = ebookRepository.authorOfEbooks(item.getId());
            AuthorDTO authorDTO = mapper.map(authors, AuthorDTO.class);
            item.setAuthorDTO(authorDTO);
        }
        EbookListDTO ebookListDTO = new EbookListDTO();
        ebookListDTO.setEbookDTOList(ebookDTOs);

        PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
        publisherDTO.setEbookListDTO(ebookListDTO);

        publisherDTO.setCountOfBook(publisherRepository.countEbooksOfPublisher(id));
        return publisherDTO;
    }

    @Override
    public PublisherListDTO top5BestSellingPublisher() {
        List<Publisher> publishers = publisherRepository.findTop5BestSellingPublishers(new PageRequest(0,5));
        List<PublisherDTO> publisherDTOS = mapper.mapAsList(publishers, PublisherDTO.class);
        for (PublisherDTO publisherDTO: publisherDTOS){
            publisherDTO.setAllBookOfPublisher(publisherRepository.sumEbooksOfPublisher(publisherDTO.getId()));
        }
        PublisherListDTO publisherListDTO = new PublisherListDTO();
        publisherListDTO.setPublisherDTOList(publisherDTOS);

        return publisherListDTO;
    }
}
