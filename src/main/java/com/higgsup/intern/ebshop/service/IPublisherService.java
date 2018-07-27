package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.dto.PublisherListDTO;

public interface IPublisherService {
    PublisherDTO findById(Long id);
    PublisherListDTO top5BestSellingPublisher();
}
