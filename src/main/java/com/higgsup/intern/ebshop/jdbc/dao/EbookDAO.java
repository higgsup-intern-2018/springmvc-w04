package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;

import java.util.List;

public interface EbookDAO {
    Ebook findById(Long id);
    List<Ebook> find(String name, Long authorId, Long publisherId, Long priceFrom, Long priceTo, String isbn);
    Ebook findByIsbn(String isbn);
    Publisher getPublisherByEbookId(Long id);
    Author infoOfAuthor(Long id);
    List<EbookOrderDTO> top10BestSeller();
    void create(Ebook ebook);
    void update(Ebook ebook);
    void updateAddedEbook(Ebook ebook);
    void delete(Long id);
}
