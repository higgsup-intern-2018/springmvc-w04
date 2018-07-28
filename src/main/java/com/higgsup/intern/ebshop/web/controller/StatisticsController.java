package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.service.IEbookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final IEbookService ebookService;

    public StatisticsController(IEbookService ebookService)
    {
        this.ebookService = ebookService;
    }

    @GetMapping("/top-10-best-seller-ebooks")
    public ResponseEntity<EbookOrderListDTO> top10BestSellers(){
        EbookOrderListDTO ebookOrderListDTO = ebookService.top10BestSellers();
        return ResponseEntity.ok(ebookOrderListDTO);
    }
}
