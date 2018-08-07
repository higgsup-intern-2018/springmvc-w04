package com.higgsup.intern.ebshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherDTO {
    private Long id;
    private String name;
    private String website;
    private String founder;
    private Integer foundedYear;
    private String address;
    private Integer countOfBooks;
    private List<EbookDTO> top5BestSellings;
    private Integer copiesSold;

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

    public Integer getCountOfBooks() {
        return countOfBooks;
    }

    public void setCountOfBooks(Integer countOfBooks) {
        this.countOfBooks = countOfBooks;
    }

    public List<EbookDTO> getTop5BestSellings() {
        return top5BestSellings;
    }

    public void setTop5BestSellings(List<EbookDTO> top5BestSellings) {
        this.top5BestSellings = top5BestSellings;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }
}
