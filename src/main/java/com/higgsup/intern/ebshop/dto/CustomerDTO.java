package com.higgsup.intern.ebshop.dto;


public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer quantity;
    private Double totalPriceOfOrders;


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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPriceOfOrders() {
        return totalPriceOfOrders;
    }

    public void setTotalPriceOfOrders(Double totalPriceOfOrders) {
        this.totalPriceOfOrders = totalPriceOfOrders;
    }
}
