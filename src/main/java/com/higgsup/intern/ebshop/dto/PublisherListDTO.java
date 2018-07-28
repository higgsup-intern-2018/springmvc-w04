package com.higgsup.intern.ebshop.dto;

import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.OrderDetails;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherListDTO {
    private List<PublisherDTO> publisherDTOList = new ArrayList<>();

    public PublisherListDTO() {
    }

    public PublisherListDTO(List<PublisherDTO> publisherDTOList) {
        this.publisherDTOList = publisherDTOList;
    }

    public List<PublisherDTO> getPublisherDTOList() {
        return publisherDTOList;
    }

    public void setPublisherDTOList(List<PublisherDTO> publisherDTOList) {
        this.publisherDTOList = publisherDTOList;
    }
}
