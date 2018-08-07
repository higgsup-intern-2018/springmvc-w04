package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookListDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.IAuthorRepository;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    private final IAuthorRepository authorRepository;
    private final IEbookRepository ebookRepository;
    private final MapperFacade mapper;

    public AuthorService(IAuthorRepository authorRepository, IEbookRepository ebookRepository, MapperFacade mapper) {
        this.authorRepository = authorRepository;
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void createAuthor(AuthorDTO authorDTO) {
        Author author = mapper.map(authorDTO, Author.class);
        authorRepository.save(author);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void update(AuthorDTO authorDTO) {
        Long id = authorDTO.getId();
        if (authorRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        Author author = mapper.map(authorDTO, Author.class);
        authorRepository.save(author);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deleteAuthor(Long id) {
        if (authorRepository.findOne(id) == null){
            throw new ServiceException(String.format("The author with id = %d has not existed!", id));
        }
        else if (authorRepository.countEbooksOfAuthor(id) == null){
            authorRepository.delete(id);
        } else {
            throw new ServiceException(String.format("Not deleted! Because the author with id = %d has some ebooks!", id));
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public AuthorDTO findAuthorById(Long id) {
        Author author = authorRepository.findOne(id);
        if (author == null) {
            throw new ResourceNotFoundException(String.format("Author with id = %d does not exist!", id));
        }
        List<Ebook> ebooks = authorRepository.top3BookOfAuthor(id, new PageRequest(0,3));

        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);
        for (EbookDTO ebookDTO : ebookDTOs) {
            Publisher publisher = ebookRepository.publisherOfEbooks(ebookDTO.getId());
            PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
            ebookDTO.setPublisherDTO(publisherDTO);
        }

        EbookListDTO ebookListDTO = new EbookListDTO();
        ebookListDTO.setEbookDTOList(ebookDTOs);

        AuthorDTO authorDTO = mapper.map(author, AuthorDTO.class);
        authorDTO.setEbookListDTO(ebookListDTO);
        authorDTO.setAllBookOfAuthor(authorRepository.countEbooksOfAuthor(id));

        return authorDTO;
    }
}
