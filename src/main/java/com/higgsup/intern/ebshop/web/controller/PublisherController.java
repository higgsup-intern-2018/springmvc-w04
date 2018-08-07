package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        publisherService.createPublisher(publisherDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponseDTO.created());
    }

    @PutMapping
    public ResponseEntity<GenericResponseDTO> updateAuthor(@RequestBody PublisherDTO publisherDTO) {
        publisherService.updatePublisher(publisherDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.updated());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteAuthorById(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable("id") Long id) {
        PublisherDTO publisherDTO = publisherService.findPublisherById(id);
        return ResponseEntity.ok(publisherDTO);
    }

}
