package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService implements IEbookService {
    private final EbookDAO ebookDAO;
    private final MapperFacade mapperFacade;

    public EbookService(EbookDAO ebookDAO, MapperFacade mapperFacade)
    {
        this.ebookDAO = ebookDAO;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public EbookOrderListDTO top10BestSellers() {
        List<EbookOrderDTO> ebookOrderDTOS = ebookDAO.top10BestSeller();

        EbookOrderListDTO ebookOrderListDTO = new EbookOrderListDTO();
        ebookOrderListDTO.setEbookOrderDTOs(ebookOrderDTOS);

        return ebookOrderListDTO;
    }
}
