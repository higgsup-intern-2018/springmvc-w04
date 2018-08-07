package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.service.IPublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final IPublisherService publisherService;

    @Autowired
    public PublisherController(IPublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @PostMapping
    public ResponseEntity<GenericResponseDTO> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        publisherService.create(publisherDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponseDTO.created());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deletePublisherById(@PathVariable("id") Long id) {
        publisherService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable("id") Long id) {
        PublisherDTO publisherDTO = publisherService.findPublisherById(id);
        return ResponseEntity.ok(publisherDTO);
    }

    @PutMapping
    public ResponseEntity<GenericResponseDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        publisherService.update(publisherDTO);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponseDTO.updated());
    }
}
