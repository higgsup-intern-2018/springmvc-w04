package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.jdbc.dao.OrderDAO;
import com.higgsup.intern.ebshop.service.IOrderService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderDAO orderDAO;
    private final MapperFacade mapper;

    public OrderService(OrderDAO orderDAO, MapperFacade mapper)
    {
        this.orderDAO = orderDAO;
        this.mapper = mapper;
    }

    public EbookOrderListDTO getEbookOrderList(Long id)
    {
        List<EbookOrderDTO> ebookOrders = orderDAO.findByOrderId(id);

        EbookOrderListDTO ebookOrderListDTO = new EbookOrderListDTO();
        ebookOrderListDTO.setEbookOrderDTOs(ebookOrders);

        return ebookOrderListDTO;
    }

    @Override
    public OrderExportDTO exportById(Long id) {
        OrderExportDTO orderExport = orderDAO.exportOrder(id);
        if (orderExport == null) {
            throw new ResourceNotFoundException(String.format("Order with id = %d does not exist!", id));
        }
        EbookOrderListDTO ebookOrderListDTO = getEbookOrderList(id);
        Long totalPrice = 0L;
        for(EbookOrderDTO ebookOrderDTO : ebookOrderListDTO.getEbookOrderDTOs())
        {
            totalPrice += ebookOrderDTO.getPrice() * ebookOrderDTO.getCopiesSold();
        }
        orderExport.setItemList(ebookOrderListDTO);
        orderExport.setTotalPrice(totalPrice);
        return orderExport;
    }
}
