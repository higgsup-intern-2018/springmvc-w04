package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.AuthorListDTO;

public interface IAuthorService {
    void update(AuthorDTO authorDTO);
    void delete(Long id);
    AuthorDTO findById(Long id);
    void create(AuthorDTO authorDTO);
}
