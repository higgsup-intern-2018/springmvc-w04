package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;

import java.util.List;

public interface EbookDAO {
    List<Ebook> find(String name, Long authorId, Long publisherId, Long priceFrom, Long priceTo, String isbn);
    List<Ebook> findTop10BestSellerEbooks();
    Ebook findByIsbn(String isbn);
    Ebook findById(Long id);
    void create(Ebook ebook);
    void update(Ebook ebook);
    void updateAddedEbook(Ebook ebook);
    void delete(Long id);
    Publisher getPublisherByEbookId(Long id);
}
