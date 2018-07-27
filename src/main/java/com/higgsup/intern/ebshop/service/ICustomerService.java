package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.CustomerListDTO;


public interface ICustomerService {
    CustomerListDTO findTop5BestBuyCustomers();
}
