package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.service.IPublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private IPublisherService publisherService;

    public PublisherController(IPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getAuthorById(@PathVariable("id") Long id) {
        PublisherDTO publisherDTO = publisherService.findById(id);
        return ResponseEntity.ok(publisherDTO);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        publisherService.create(publisherDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponseDTO.created());
    }

    @PutMapping
    public ResponseEntity<GenericResponseDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        publisherService.update(publisherDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.updated());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteAuthorById(@PathVariable("id") Long id) {
        publisherService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }
}
