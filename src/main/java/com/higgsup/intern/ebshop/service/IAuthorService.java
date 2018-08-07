package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.AuthorDTO;

public interface IAuthorService {
    AuthorDTO findById(Long id);
    void create(AuthorDTO authorDTO);
    void update(AuthorDTO authorDTO);
    void delete(Long id);
}
