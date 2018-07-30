package com.higgsup.intern.ebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class EbookOrderListDTO {
    private List<EbookOrderDTO> ebookOrderDTOs = new ArrayList<>();

    public List<EbookOrderDTO> getEbookOrderDTOs() {
        return ebookOrderDTOs;
    }

    public void setEbookOrderDTOs(List<EbookOrderDTO> ebookOrderDTOs) {
        this.ebookOrderDTOs = ebookOrderDTOs;
    }
}
