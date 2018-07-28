package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderExportDTO> exportById(@PathVariable("id") Long id) {
        OrderExportDTO orderExportDTO = orderService.exportById(id);
        return ResponseEntity.ok(orderExportDTO);
    }
}
