package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.CustomerDTO;


import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getTop5MostBuying();
    List<CustomerDTO> getTop5HighestPrice();
}
