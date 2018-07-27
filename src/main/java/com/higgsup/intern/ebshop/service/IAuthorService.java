package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.AuthorListDTO;

public interface IAuthorService {
    void create(AuthorDTO authorDTO);
    AuthorListDTO findTop5BestSellingAuthors();
}
