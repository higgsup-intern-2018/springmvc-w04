package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.AuthorListDTO;
import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    private final AuthorDAO authorDAO;
    private final MapperFacade mapper;

    public AuthorService(AuthorDAO authorDAO,
                         MapperFacade mapper) {
        this.authorDAO = authorDAO;
        this.mapper = mapper;
    }

    @Override
    public void create(AuthorDTO authorDTO) {
        Author author = mapper.map(authorDTO, Author.class);
        authorDAO.create(author);
    }

    @Override
    public AuthorListDTO findTop5BestSellingAuthors() {
        List<Author> authors = authorDAO.findTop5BestSellingAuthors();
        List<AuthorDTO> authorDTOs = mapper.mapAsList(authors, AuthorDTO.class);
        AuthorListDTO authorListDTO = new AuthorListDTO();
        authorListDTO.setAuthorDTOs(authorDTOs);
        return authorListDTO;
    }
}
