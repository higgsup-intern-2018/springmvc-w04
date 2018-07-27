package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import com.higgsup.intern.ebshop.dto.PersonListDTO;
import com.higgsup.intern.ebshop.service.IAuthorService;
import com.higgsup.intern.ebshop.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final ICustomerService customerService;
    @Autowired
    public StatisticsController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> findTop5HighestOrderPriceCustomers() {
        CustomerListDTO customerListDTO = customerService.findTop5HighestOrderPriceCustomers();
        return ResponseEntity.ok(customerListDTO);
    }

}
