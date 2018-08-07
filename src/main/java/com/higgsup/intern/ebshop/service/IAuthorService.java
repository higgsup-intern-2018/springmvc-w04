package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.AuthorDTO;

public interface IAuthorService {
    void createAuthor(AuthorDTO authorDTO);
    void update(AuthorDTO authorDTO);
    void deleteAuthor(Long id);
    AuthorDTO findAuthorById(Long id);
}
