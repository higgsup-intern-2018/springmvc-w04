package com.higgsup.intern.ebshop.jpa.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
public class Customer {
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
    private String email;
    private String phone;
    private String address;
   // private Integer quantity;
    //private Double totalPriceOfOrders;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  /*  public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }*/

   /* public Double getTotalPriceOfOrders() {
        return totalPriceOfOrders;

    public void setTotalPriceOfOrders(Double totalPriceOfOrders) {
        this.totalPriceOfOrders = totalPriceOfOrders;
    }*/
}
