package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.service.IEbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

