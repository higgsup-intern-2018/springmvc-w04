package com.higgsup.intern.ebshop.jdbc.model;

public class OrderDetails {
    private long id;
    private long orderId;
    private long ebookId;
    private Integer quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getEbookId() {
        return ebookId;
    }

    public void setEbookId(long ebookId) {
        this.ebookId = ebookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
