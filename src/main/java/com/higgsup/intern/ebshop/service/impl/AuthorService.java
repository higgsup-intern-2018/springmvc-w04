package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    private final AuthorDAO authorDAO;
    private final MapperFacade mapper;

    public AuthorService(AuthorDAO authorDAO, MapperFacade mapper) {
        this.authorDAO = authorDAO;
        this.mapper = mapper;
    }

    @Override
    public AuthorDTO findById(Long id) {
        Author author = authorDAO.findById(id);
        if (author == null) {
            throw new ResourceNotFoundException(String.format("Author with id = %d does not exist!", id));
        }
        return mapper.map(author, AuthorDTO.class);
    }

    @Override
    public void update(AuthorDTO authorDTO) {
        Long id = authorDTO.getId();
        if (authorDAO.findById(id) == null) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        Author author = mapper.map(authorDTO, Author.class);
        authorDAO.update(author);

    }

    @Override
    public void delete(Long id) {
        if (authorDAO.findById(id) == null) {
            throw new ServiceException(String.format("Author with id = %d does not exist!", id));
        }
        if (authorDAO.countEbooksOfAAuthor(id) == 0) {
            authorDAO.delete(id);
        } else {
            throw new ServiceException(String.format("Not deleted! Because the author with id = %d has some ebooks!", id));
        }


    }

}