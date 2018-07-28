package com.higgsup.intern.ebshop.dto;

public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
    private String description;
    private String website;
    private String organization;
    private EbookListDTO ebookListDTO;
    private Integer allBookOfAuthor;

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

    public EbookListDTO getEbookListDTO() {
        return ebookListDTO;
    }

    public void setEbookListDTO(EbookListDTO ebookListDTO) {
        this.ebookListDTO = ebookListDTO;
    }

    public Integer getAllBookOfAuthor() {
        return allBookOfAuthor;
    }

    public void setAllBookOfAuthor(Integer allBookOfAuthor) {
        this.allBookOfAuthor = allBookOfAuthor;
    }
}
