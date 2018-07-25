package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Publisher;

import java.util.List;

public interface PublisherDAO {
    Publisher findById(Long id);
    List<Publisher> findTop5BestSellingPublishers();
    void create(Publisher publisher);
    void update(Publisher publisher);
    void delete(Long id);
}
