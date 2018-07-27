package com.higgsup.intern.ebshop.web.controller;

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
}
