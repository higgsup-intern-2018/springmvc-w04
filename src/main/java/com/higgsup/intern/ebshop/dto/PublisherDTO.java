package com.higgsup.intern.ebshop.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PublisherDTO {
    private Long id;
    private String name;
    private String website;
    private String founder;
    private Integer foundedYear;
    private String address;
    private Integer allBookOfPublisher;
    private EbookListDTO ebookListDTO;
    private Integer countOfBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAllBookOfPublisher() {
        return allBookOfPublisher;
    }

    public void setAllBookOfPublisher(Integer allBookOfPublisher) {
        this.allBookOfPublisher = allBookOfPublisher;
    }

    public EbookListDTO getEbookListDTO() {
        return ebookListDTO;
    }

    public void setEbookListDTO(EbookListDTO ebookListDTO) {
        this.ebookListDTO = ebookListDTO;
    }

    public Integer getCountOfBook() {
        return countOfBook;
    }

    public void setCountOfBook(Integer countOfBook) {
        this.countOfBook = countOfBook;
    }
}