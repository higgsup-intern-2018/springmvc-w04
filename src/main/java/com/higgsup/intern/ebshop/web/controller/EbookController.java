package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.service.IEbookService;
import com.higgsup.intern.ebshop.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteEbookById(@PathVariable("id") Long id) {
        ebookService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }
}
