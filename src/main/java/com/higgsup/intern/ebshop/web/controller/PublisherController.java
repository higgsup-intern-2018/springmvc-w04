package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.service.IPublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublisherController
{
    private final IPublisherService publisherService;

    public PublisherController(IPublisherService publisherService)
    {
        this.publisherService = publisherService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deletePublisherById(@PathVariable("id") Long id) {
        publisherService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }
}
