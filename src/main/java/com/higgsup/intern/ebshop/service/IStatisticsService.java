package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.dto.ItemInfoDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;

import java.util.List;

public interface IStatisticsService {

    List<ItemInfoDTO> findTop10BestSellingEbooks();
    List<CustomerDTO> findTop5HighestPrice();
    List<CustomerDTO> findTop5MostBuyCustomers();
    List<PublisherDTO> findTop5BestSellingPublisher();
    List<AuthorDTO> findTop5BestSellingAuthors();
}
