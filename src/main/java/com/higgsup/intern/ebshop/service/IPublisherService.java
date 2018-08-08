package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.dto.PublisherListDTO;

public interface IPublisherService {
    void createPublisher(PublisherDTO publisherDTO);
    void updatePublisher(PublisherDTO publisherDTO);
    void deletePublisher(Long id);
    PublisherDTO findPublisherById(Long id);
    PublisherListDTO top5BestSellingPublisher();
}
