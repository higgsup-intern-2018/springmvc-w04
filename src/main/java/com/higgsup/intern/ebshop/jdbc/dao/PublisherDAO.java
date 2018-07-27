package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Publisher;

public interface PublisherDAO {
    void create(Publisher publisher);
    void delete(Long id);
}
