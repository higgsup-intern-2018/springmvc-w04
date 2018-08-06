package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.IAuthorRepository;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    private final IAuthorRepository authorRepository;
    private final MapperFacade mapper;
    private final EbookRepository ebookRepository;
    private final IEbookRepository iEbookRepository;
    private final IAuthorRepository iAuthorRepository;

    public AuthorService(IAuthorRepository authorRepository, MapperFacade mapper, EbookRepository ebookRepository, IEbookRepository iEbookRepository, IAuthorRepository iAuthorRepository) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
        this.ebookRepository = ebookRepository;
        this.iEbookRepository = iEbookRepository;
        this.iAuthorRepository = iAuthorRepository;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AuthorDTO findById(Long id) {
        Author author = authorRepository.findOne(id);
        if (author == null) {
            throw new ResourceNotFoundException(String.format("Author with id = %d does not exist!", id));
        }
        List<Ebook> ebooks = iAuthorRepository.getTop3BooksOfAuthor(id);
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
        if (authorRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        Author author = mapper.map(authorDTO, Author.class);
        authorRepository.save(author);

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (authorRepository.findOne(id).getYearOfBirth() == 0) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        authorRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void create(AuthorDTO authorDTO) {
        Author author = mapper.map(authorDTO, Author.class);
        authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AuthorListDTO findTop5BestSellingAuthors() {
        List<Author> authors = iAuthorRepository.findTop5BestSellingAuthors();
        List<AuthorDTO> authorDTOs = mapper.mapAsList(authors, AuthorDTO.class);
        AuthorListDTO authorListDTO = new AuthorListDTO();
        authorListDTO.setAuthorDTOs(authorDTOs);
        return authorListDTO;
    }
}
