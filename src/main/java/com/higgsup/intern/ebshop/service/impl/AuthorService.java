package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.IAuthorRepository;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.AuthorListDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    private final MapperFacade mapper;
    private final IAuthorRepository iAuthorRepository;
    private final IEbookRepository iEbookRepository;

    public AuthorService(MapperFacade mapper, IAuthorRepository iAuthorRepository, IEbookRepository iEbookRepository) {
        this.mapper = mapper;
        this.iAuthorRepository = iAuthorRepository;
        this.iEbookRepository = iEbookRepository;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AuthorDTO findById(Long id) {
        Author author = iAuthorRepository.getById(id);
        if (author == null) {
            throw new ResourceNotFoundException(String.format("Author with id = %d does not exist!", id));
        }
        List<Ebook> ebooks = iAuthorRepository.getTop3BooksOfAuthor(id).subList(0, 3);

        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);
        for (EbookDTO ebookDTO : ebookDTOs) {
            Publisher publisher = iEbookRepository.getPublisherByEbookId(ebookDTO.getId());
            PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
            ebookDTO.setPublisherDTO(publisherDTO);
        }

        EbookListDTO ebookListDTO = new EbookListDTO();
        ebookListDTO.setEbookDTOList(ebookDTOs);

        AuthorDTO authorDTO = mapper.map(author, AuthorDTO.class);
        authorDTO.setEbookListDTO(ebookListDTO);

        return authorDTO;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(AuthorDTO authorDTO) {
        Long id = authorDTO.getId();
        if (iAuthorRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        Author author = mapper.map(authorDTO, Author.class);
        iAuthorRepository.save(author);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (iAuthorRepository.findOne(id).getYearOfBirth() == 0) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        if (iAuthorRepository.countEbooksOfAnAuthor(id) == 0) {
            iAuthorRepository.delete(id);
        } else {
            throw new ServiceException(String.format("Not deleted! Because the author with id = %d has some ebooks!", id));
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void create(AuthorDTO authorDTO) {
        Author author = mapper.map(authorDTO, Author.class);
        iAuthorRepository.save(author);
    }

    }

