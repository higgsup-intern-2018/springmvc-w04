package com.higgsup.intern.ebshop.jpa.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
public class Author {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private String firstname;
    private String lastname;
    private Integer yearOfBirth;
    private String description;
    private String website;
    private String organization;
  //  private Integer countOfBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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

   /* public Integer getCountOfBooks() {
        return countOfBooks;
    }

    public void setCountOfBooks(Integer countOfBooks) {
        this.countOfBooks = countOfBooks;
    }*/
}

