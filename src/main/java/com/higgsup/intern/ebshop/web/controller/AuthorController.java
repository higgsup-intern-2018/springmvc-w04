package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final IAuthorService authorService;

    @Autowired
    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteAuthorById(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GenericResponseDTO.deleted());
    }
}
