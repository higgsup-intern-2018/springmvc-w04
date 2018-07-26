package com.higgsup.intern.ebshop.dto;



import java.util.ArrayList;
import java.util.List;

public class EbookListDTO {

    private List<EbookDTO> ebookDTOList = new ArrayList<EbookDTO>();

    public List<EbookDTO> getEbookDTOList() {
        return ebookDTOList;
    }

    public void setEbookDTOList(List<EbookDTO> ebookDTOList) {
        this.ebookDTOList = ebookDTOList;
    }
}
