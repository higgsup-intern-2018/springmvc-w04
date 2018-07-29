package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import com.higgsup.intern.ebshop.service.IAuthorService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.AuthorListDTO;
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
    private final EbookDAO ebookDAO;

    public AuthorService(AuthorDAO authorDAO,
                         MapperFacade mapper, EbookDAO ebookDAO) {
        this.authorDAO = authorDAO;
        this.mapper = mapper;
        this.ebookDAO = ebookDAO;
    }

    @Override
    public AuthorDTO findById(Long id) {
        Author author = authorDAO.findById(id);
        if (author == null) {
            throw new ResourceNotFoundException(String.format("Author with id = %d does not exist!", id));
        }
        List<Ebook> ebooks = authorDAO.getTop3BooksOfAuthor(id);

        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks, EbookDTO.class);
        for (EbookDTO ebookDTO : ebookDTOs) {
            Publisher publisher = ebookDAO.getPublisherByEbookId(ebookDTO.getId());
            PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);
            ebookDTO.setPublisherDTO(publisherDTO);
        }

        EbookListDTO ebookListDTO = new EbookListDTO();
        ebookListDTO.setEbookDTOList(ebookDTOs);

        AuthorDTO authorDTO = mapper.map(author, AuthorDTO.class);
        authorDTO.setEbookListDTO(ebookListDTO);


        authorDTO.setAllBookOfAuthor(authorDAO.getBookCount(id));
        return authorDTO;
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
        if (authorDAO.countEbooksOfAnAuthor(id) == 0) {
            authorDAO.delete(id);
        } else {
            throw new ServiceException(String.format("Not deleted! Because the author with id = %d has some ebooks!", id));
        }
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
