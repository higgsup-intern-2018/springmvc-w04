package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;

public interface IPublisherService {
    void create(PublisherDTO publisherDTO);
}
