package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.IPublisherReposiory;
import com.higgsup.intern.ebshop.jpa.repo.PublisherRepository;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService implements IPublisherService {
    private final PublisherRepository publisherRepository;
    private final EbookRepository ebookRepository;
    private final MapperFacade mapper;
    private final IPublisherReposiory iPublisherReposiory;
    private final IEbookRepository iEbookRepository;

    public PublisherService(PublisherRepository publisherRepository, EbookRepository ebookRepository, MapperFacade mapper, IPublisherReposiory iPublisherReposiory, IEbookRepository iEbookRepository) {
        this.publisherRepository = publisherRepository;
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
        this.iPublisherReposiory = iPublisherReposiory;
        this.iEbookRepository = iEbookRepository;
    }


    @Override
    public void create(PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(Long id) {
        if (iPublisherReposiory.findOne(id).getFoundedYear() == 0) {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
       if (iPublisherReposiory.countBookOfPublisher(id) > 0) {
            throw new ServiceException(String.format("Publisher with id = %d still have book in it!", id));
        }
        publisherRepository.delete(id);
    }

    @Override
    public void update(PublisherDTO publisherDTO) {
        Long id = publisherDTO.getId();
        if (iPublisherReposiory.findOne(id).getName() == null) {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }

        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    public PublisherDTO findById(Long id) {
        Publisher publisher = iPublisherReposiory.getById(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(String.format("Publisher with id = %d does not exist!", id));
        }

        List<Ebook> ebooks = iPublisherReposiory.top5BookOfPublisher(id);
        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);

        for (EbookDTO item : ebookDTOs) {
            Author authors = iEbookRepository.infoOfAuthor(item.getId());
            AuthorDTO authorDTO = mapper.map(authors, AuthorDTO.class);
            item.setAuthorDTO(authorDTO);
        }
        EbookListDTO ebookListDTO = new EbookListDTO();
        ebookListDTO.setEbookDTOList(ebookDTOs);

        PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
        publisherDTO.setEbookListDTO(ebookListDTO);

        publisherDTO.setAllBookOfPublisher(iPublisherReposiory.countBookOfPublisher(id));
        return publisherDTO;

    }

    @Override
    public PublisherListDTO top5BestSellingPublisher() {
       List<Publisher> publishers = iPublisherReposiory.findTop5BestSellingPublishers();
        List<PublisherDTO> publisherDTOS = mapper.mapAsList(publishers, PublisherDTO.class);

        PublisherListDTO publisherListDTO = new PublisherListDTO();
        publisherListDTO.setPublisherDTOList(publisherDTOS);

        return publisherListDTO;
    }
}
