package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.PublisherListDTO;
import com.higgsup.intern.ebshop.service.IPublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final IPublisherService publisherService;

    public StatisticsController(IPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/top-5-best-selling-publishers")
    public ResponseEntity<PublisherListDTO> getTop5Publisher() {
        PublisherListDTO top5Publisher = publisherService.top5BestSellingPublisher();
        return ResponseEntity.ok(top5Publisher);
    }
}
