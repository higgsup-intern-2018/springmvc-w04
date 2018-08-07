package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final IStatisticsService statisticsService;

    @Autowired
    public StatisticsController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/top-5-highest-order-price-customers")
    public ResponseEntity<List<CustomerDTO>> findTop5HighestOrderPriceCustomers() {
        List<CustomerDTO> customerListDTO = statisticsService.findTop5HighestPrice();
        return ResponseEntity.ok(customerListDTO);
    }

    @GetMapping("/top-10-best-seller-ebooks")
    public ResponseEntity<List<ItemInfoDTO>> top10BestSellers() {
        List<ItemInfoDTO> itemInfoDTOS = statisticsService.findTop10BestSellingEbooks();
        return ResponseEntity.ok(itemInfoDTOS);
    }

    @GetMapping("/top-5-best-buy-customers")
    public ResponseEntity<List<CustomerDTO>> getTop5BestBuyCustomers() {
        List<CustomerDTO> customerListDTO = statisticsService.findTop5MostBuyCustomers();
        return ResponseEntity.ok(customerListDTO);
    }

    @GetMapping("/top-5-best-selling-publishers")
    public ResponseEntity<List<PublisherDTO>> getTop5Publisher() {
        List<PublisherDTO> top5Publisher = statisticsService.findTop5BestSellingPublisher();
        return ResponseEntity.ok(top5Publisher);
    }

    @GetMapping("/top-5-best-selling-authors")
    public ResponseEntity<List<AuthorDTO>> getTop5Author() {
        List<AuthorDTO> top5Author = statisticsService.findTop5BestSellingAuthors();
        return ResponseEntity.ok(top5Author);
    }
}
