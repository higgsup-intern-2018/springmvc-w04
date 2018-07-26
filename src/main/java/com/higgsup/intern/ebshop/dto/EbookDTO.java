package com.higgsup.intern.ebshop.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EbookDTO {
    private Long id;
    private String title;
    private String isbn;
    private String description;
    private AuthorDTO authorDTO;
    private PublisherDTO publisherDTO;
    private Date publicationDate;
    private Integer page;
    private Long price;
    private Integer quantity;
    private AuthorListDTO authorListDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public PublisherDTO getPublisherDTO() {
        return publisherDTO;
    }

    public void setPublisherDTO(PublisherDTO publisherDTO) {
        this.publisherDTO = publisherDTO;
    }

    public AuthorListDTO getAuthorListDTO() {
        return authorListDTO;
    }

    public void setAuthorListDTO(AuthorListDTO authorListDTO) {
        this.authorListDTO = authorListDTO;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
