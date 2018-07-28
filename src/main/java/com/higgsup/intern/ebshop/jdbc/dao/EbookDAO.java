package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Ebook;

import java.util.List;

public interface EbookDAO {
    Ebook findById(Long id);
    void update(Ebook ebook);
    Ebook findByIsbn(String isbn);
}
