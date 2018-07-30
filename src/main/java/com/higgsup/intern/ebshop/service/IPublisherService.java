package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PublisherDTO;


import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.dto.PublisherListDTO;

public interface IPublisherService {
    void create(PublisherDTO publisherDTO);
    void delete(Long id);
    void update(PublisherDTO publisherDTO);
    PublisherListDTO top5BestSellingPublisher();
    PublisherDTO findById(Long id);
}
