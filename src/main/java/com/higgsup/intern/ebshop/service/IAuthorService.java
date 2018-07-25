package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.jdbc.model.Author;

import java.util.List;

public interface IAuthorService {
    AuthorDTO findById(Long id);
    List<Author> findTop5BestSellingAuthors();
    void create(Author author);
    void update(Author author);
    void delete(Long id);
}
