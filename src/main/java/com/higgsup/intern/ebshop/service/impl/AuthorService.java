package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import com.higgsup.intern.ebshop.jpa.repo.AuthorRepository;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    private final AuthorRepository authorRepository;
    private final EbookRepository ebookRepository;
    private final MapperFacade mapper;

    public AuthorService(AuthorRepository authorRepository, EbookRepository ebookRepository, MapperFacade mapper)
    {
        this.authorRepository = authorRepository;
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AuthorDTO findById(Long id) {
        Author author = authorRepository.findOne(id);
        if(author == null)
        {
            throw new ResourceNotFoundException(String.format("Author with id = %d does not exist!", id));
        }
        AuthorDTO authorDTO =  mapper.map(author, AuthorDTO.class);
        authorDTO.setCountBook(authorRepository.countEbooksOfAuthor(id));
        List<Ebook> ebooks = authorRepository.findTop3BestSelling(id, new PageRequest(0,3));
        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);
        for(EbookDTO ebookDTO : ebookDTOs){
            Publisher publisher = ebookRepository.findPublisherByEbookId(ebookDTO.getId());
            PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
            ebookDTO.setPublisherDTO(publisherDTO);
        }
        authorDTO.setTop3BestSelling(ebookDTOs);
        return authorDTO;
    }

    public void create(AuthorDTO authorDTO)
    {
        Author author = mapper.map(authorDTO, Author.class);
        authorRepository.save(author);
    }

    public void update(AuthorDTO authorDTO)
    {
        Long id = authorDTO.getId();
        if(authorRepository.findOne(id) == null)
        {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }

        Author author = mapper.map(authorDTO, Author.class);
        authorRepository.save(author);
    }

    public void delete(Long id) {
        if (authorRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        if(authorRepository.countEbooksOfAuthor(id) > 0)
        {
            throw new ServiceException(String.format("Author with id = %d still has books!", id));
        }
        authorRepository.delete(id);
    }
}
