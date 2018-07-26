package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;

import java.util.List;

public interface PublisherDAO {
    Publisher findbyId(Long id);
    List<Publisher> findTop5BestSellingPublishers();
    void create(Publisher publisher);
    void update(Publisher publisher);
    void delete(Long id);
    Integer countBookOfPublisher(Long id);
    List<Ebook> top5BookOfPublisher(Long id);
}
