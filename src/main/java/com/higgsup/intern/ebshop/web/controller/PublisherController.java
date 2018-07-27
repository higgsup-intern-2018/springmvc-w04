package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.dto.PublisherListDTO;
import com.higgsup.intern.ebshop.service.IPublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final IPublisherService publisherService;

    public PublisherController(IPublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPersonById(@PathVariable("id") Long id) {
        PublisherDTO publisherDTO = publisherService.findById(id);
        return ResponseEntity.ok(publisherDTO);
    }
    @GetMapping ("/top-5-best-selling-publishers")
    public ResponseEntity<PublisherListDTO> getAllPersons() {
        PublisherListDTO top5Publisher = publisherService.top5BestSellingPublisher();
        return ResponseEntity.ok(top5Publisher);
    }
}
