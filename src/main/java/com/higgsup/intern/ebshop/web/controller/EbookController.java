package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookListDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.service.IEbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebooks")
public class EbookController {
    private final IEbookService ebookService;

    @Autowired
    public EbookController(IEbookService ebookService) {
        this.ebookService = ebookService;
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> createEbook(@RequestBody EbookDTO ebookDTO) {
        GenericResponseDTO responseDTO = ebookService.create(ebookDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<GenericResponseDTO> updateEbook(@RequestBody EbookDTO ebookDTO) {
        ebookService.update(ebookDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.updated());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteEbookById(@PathVariable("id") Long id) {
        ebookService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }
    @GetMapping("/{name}/{authorId}/{publisherId}/{priceFrom}/{priceTo}/{isbn}")
    public ResponseEntity<EbookListDTO> findEbook(@PathVariable("name") String name,
                                                  @PathVariable("authorId") Long authorId,
                                                  @PathVariable("publisherId") Long publisherId,
                                                  @PathVariable("priceFrom") Double priceFrom,
                                                  @PathVariable("priceTo") Double priceTo,
                                                  @PathVariable("isbn") String isbn) {
        EbookListDTO ebookListDTO = ebookService.searchEbook(name, authorId, publisherId, priceFrom, priceTo, isbn);
        return ResponseEntity.ok(ebookListDTO);
    }
}

