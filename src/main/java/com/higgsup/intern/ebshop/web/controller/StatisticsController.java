package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.AuthorListDTO;
import com.higgsup.intern.ebshop.dto.CustomerListDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.dto.PublisherListDTO;
import com.higgsup.intern.ebshop.service.IAuthorService;
import com.higgsup.intern.ebshop.service.ICustomerService;
import com.higgsup.intern.ebshop.service.IEbookService;
import com.higgsup.intern.ebshop.service.IPublisherService;
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
    private final IPublisherService publisherService;
    private final IAuthorService authorService;

    @Autowired
    public StatisticsController(IEbookService ebookService, ICustomerService customerService, IPublisherService publisherService, IAuthorService authorService) {
        this.ebookService = ebookService;
        this.customerService = customerService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

//    @GetMapping("/top-5-highest-order-price-customers")
//    public ResponseEntity<CustomerListDTO> findTop5HighestOrderPriceCustomers() {
//        CustomerListDTO customerListDTO = customerService.findTop5HighestOrderPriceCustomers();
//        return ResponseEntity.ok(customerListDTO);
//    }

    @GetMapping("/top-10-best-seller-ebooks")
    public ResponseEntity<EbookOrderListDTO> top10BestSellers() {
        EbookOrderListDTO ebookOrderListDTO = ebookService.top10BestSellers();
        return ResponseEntity.ok(ebookOrderListDTO);
    }

//    @GetMapping("/top-5-best-buy-customers")
//    public ResponseEntity<CustomerListDTO> getTop5BestBuyCustomers() {
//        CustomerListDTO customerListDTO = customerService.findTop5BestBuyCustomers();
//        return ResponseEntity.ok(customerListDTO);
//    }
//
//    @GetMapping("/top-5-best-selling-publishers")
//    public ResponseEntity<PublisherListDTO> getTop5Publisher() {
//        PublisherListDTO top5Publisher = publisherService.top5BestSellingPublisher();
//        return ResponseEntity.ok(top5Publisher);
//    }

    @GetMapping("/top-5-best-selling-authors")
    public ResponseEntity<AuthorListDTO> getTop5Author() {
        AuthorListDTO top5Author = authorService.findTop5BestSellingAuthors();
        return ResponseEntity.ok(top5Author);
    }
}