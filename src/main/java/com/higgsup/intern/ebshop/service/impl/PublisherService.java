package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepositoy;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.IPublisherRepository;
import com.higgsup.intern.ebshop.jpa.repo.PublisherRepository;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService implements IPublisherService {
    private final PublisherRepository publisherRepository;
    private final IPublisherRepository iPublisherRepository;
    private final EbookRepositoy ebookRepositoy;
    private final IEbookRepository iEbookRepository;
    private final MapperFacade mapper;

    public PublisherService(PublisherRepository publisherRepository, IPublisherRepository iPublisherRepository, EbookRepositoy ebookRepositoy, IEbookRepository iEbookRepository, MapperFacade mapper) {
        this.publisherRepository = publisherRepository;
        this.iPublisherRepository = iPublisherRepository;
        this.ebookRepositoy = ebookRepositoy;
        this.iEbookRepository = iEbookRepository;
        this.mapper = mapper;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void create(PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (iPublisherRepository.findById(id).getFoundedYear() == 0) {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
        if (iPublisherRepository.countBookOfPublisher(id) > 0) {
            throw new ServiceException(String.format("Publisher with id = %d still have book in it!", id));
        }
        publisherRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(PublisherDTO publisherDTO) {
        Long id = publisherDTO.getId();
        if (iPublisherRepository.findById(id).getName() == null) {
            throw new ServiceException(String.format("Person with id = %d does not exist!", id));
        }

        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public PublisherDTO findById(Long id) {
        Publisher publisher = publisherRepository.findOne(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(String.format("Publisher with id = %d does not exist!", id));
        }

        List<Ebook> ebooks = iPublisherRepository.top5BookOfPublisher(id);
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

        publisherDTO.setAllBookOfPublisher(iPublisherRepository.countBookOfPublisher(id));
        return publisherDTO;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public PublisherListDTO top5BestSellingPublisher() {
        List<Publisher> publishers = iPublisherRepository.findTop5BestSellingPublishers();
        List<PublisherDTO> publisherDTOS = mapper.mapAsList(publishers, PublisherDTO.class);

        PublisherListDTO publisherListDTO = new PublisherListDTO();
        publisherListDTO.setPublisherDTOList(publisherDTOS);

        return publisherListDTO;
    }
}
