package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PublisherDTO;


public interface IPublisherService {
    void create(PublisherDTO publisherDTO);
    void delete(Long id);
}
