package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Publisher;

public interface PublisherDAO {
    Publisher findById(Long id);
    void delete(Long id);
    Integer countBook(Long id);
}
