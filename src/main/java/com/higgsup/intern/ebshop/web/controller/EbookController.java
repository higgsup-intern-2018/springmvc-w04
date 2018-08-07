package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.service.IEbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebooks")
public class EbookController {

    private final IEbookService ebookService;

    public EbookController(IEbookService ebookService)
    {
        this.ebookService = ebookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EbookDTO> getEbookById(@PathVariable("id") Long id) {
        EbookDTO ebookDTO = ebookService.findById(id);
        return ResponseEntity.ok(ebookDTO);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> createEbook(@RequestBody EbookDTO ebookDTO)
    {
        ebookService.create(ebookDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponseDTO.created());
    }

    @PutMapping
    public ResponseEntity<GenericResponseDTO> updateEbook(@RequestBody EbookDTO ebookDTO)
    {
        ebookService.update(ebookDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.updated());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteEbook(@PathVariable("id") Long id)
    {
        ebookService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }
}
