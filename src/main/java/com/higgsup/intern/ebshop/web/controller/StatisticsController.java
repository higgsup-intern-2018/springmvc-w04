package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.service.IStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final IStatisticsService statisticsService;

    public StatisticsController(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/top-5-highest-order-price-customers")
    public ResponseEntity<List<CustomerDTO>> findTop5HighestPrice() {
        List<CustomerDTO> customerDTOs = statisticsService.findTop5HighestPrice();
        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/top-10-best-seller-ebooks")
    public ResponseEntity<List<EbookInfoDTO>> findTop10BestSellingEbooks() {
        List<EbookInfoDTO> top10Ebooks = statisticsService.findTop10BestSellingEbooks();
        return ResponseEntity.ok(top10Ebooks);
    }

    @GetMapping("/top-5-best-buy-customers")
    public ResponseEntity<List<CustomerDTO>> findTop5MostBuyCustomers() {
        List<CustomerDTO> customerListDTO = statisticsService.findTop5MostBuyCustomers();
        return ResponseEntity.ok(customerListDTO);
    }

    @GetMapping("/top-5-best-selling-publishers")
    public ResponseEntity<List<PublisherDTO>> findTop5Publisher() {
        List<PublisherDTO> top5Publisher = statisticsService.findTop5BestSellingPublisher();
        return ResponseEntity.ok(top5Publisher);
    }

    @GetMapping("/top-5-best-selling-authors")
    public ResponseEntity<List<AuthorDTO>> findTop5Author() {
        List<AuthorDTO> top5Author = statisticsService.findTop5BestSellingAuthors();
        return ResponseEntity.ok(top5Author);
    }
}
