package com.higgsup.intern.ebshop.web.controller;

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
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPersonById(@PathVariable("id") Long id) {
        PublisherDTO publisherDTO = publisherService.findById(id);
        return ResponseEntity.ok(publisherDTO);
    }
    @PutMapping
    public ResponseEntity<GenericResponseDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        publisherService.update(publisherDTO);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponseDTO.updated());
    }
}
