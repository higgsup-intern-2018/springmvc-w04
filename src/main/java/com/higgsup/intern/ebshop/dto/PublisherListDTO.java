package com.higgsup.intern.ebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class PublisherListDTO {
    private List<PublisherDTO> publisherDTOList = new ArrayList<>();

    public List<PublisherDTO> getPublisherDTOList() {
        return publisherDTOList;
    }

    public void setPublisherDTOList(List<PublisherDTO> publisherDTOList) {
        this.publisherDTOList = publisherDTOList;
    }
}
