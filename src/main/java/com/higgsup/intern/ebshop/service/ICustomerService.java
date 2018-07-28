package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import java.util.List;

public interface ICustomerService {
    CustomerListDTO findTop5BestBuyCustomers();
    CustomerListDTO findTop5HighestOrderPriceCustomers();
}
