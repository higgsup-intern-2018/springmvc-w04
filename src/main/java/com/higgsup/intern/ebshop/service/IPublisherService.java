package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PublisherDTO;

public interface IPublisherService {
    PublisherDTO findById(Long id);
}
