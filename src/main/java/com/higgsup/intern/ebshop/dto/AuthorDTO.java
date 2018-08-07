package com.higgsup.intern.ebshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
    private String description;
    private String website;
    private String organization;
    private List<EbookDTO> top3BestSelling;
    private Integer countBook;
    private Integer copiesSold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<EbookDTO> getTop3BestSelling() {
        return top3BestSelling;
    }

    public void setTop3BestSelling(List<EbookDTO> top3BestSelling) {
        this.top3BestSelling = top3BestSelling;
    }

    public Integer getCountBook() {
        return countBook;
    }

    public void setCountBook(Integer countBook) {
        this.countBook = countBook;
    }

    public Integer getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }
}
