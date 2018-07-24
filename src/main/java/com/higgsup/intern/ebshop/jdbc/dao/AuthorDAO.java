package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Author;

import java.util.List;

public interface AuthorDAO {
    Author findbyId(Long id);
    List<Author> findTop5BestSellingAuthors();
    void create(Author author);
    void update(Author author);
    void delete(Long id);
}
