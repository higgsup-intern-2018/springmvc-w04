package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.service.ICustomerService;
import com.higgsup.intern.ebshop.service.IEbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final IEbookService ebookService;
    private final ICustomerService customerService;

    @Autowired
    public StatisticsController(IEbookService ebookService, ICustomerService customerService) {
        this.ebookService = ebookService;
        this.customerService = customerService;
    }

    @GetMapping("/top-10-best-seller-ebooks")
    public ResponseEntity<EbookOrderListDTO> top10BestSellers(){
        EbookOrderListDTO ebookOrderListDTO = ebookService.top10BestSellers();
        return ResponseEntity.ok(ebookOrderListDTO);
    }


    @GetMapping("/top-5-best-buy-customers")
    public ResponseEntity<CustomerListDTO> getTop5BestBuyCustomers() {
        CustomerListDTO customerListDTO = customerService.findTop5BestBuyCustomers();
        return ResponseEntity.ok(customerListDTO);
    }
}
