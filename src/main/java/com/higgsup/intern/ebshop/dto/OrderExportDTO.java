package com.higgsup.intern.ebshop.dto;

public class OrderExportDTO {
    private Long id;
    private String customerFirstName;
    private String customerLastName;
    private String email;
    private String phone;
    private EbookOrderListDTO itemList;
    private Long totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
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

    public EbookOrderListDTO getItemList() {
        return itemList;
    }

    public void setItemList(EbookOrderListDTO itemList) {
        this.itemList = itemList;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
