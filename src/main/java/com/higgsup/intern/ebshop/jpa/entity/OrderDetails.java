package com.higgsup.intern.ebshop.jpa.entity;

import javax.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn
    private Orders orders;

    @OneToOne
    @JoinColumn
    private Ebook ebook;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Ebook getEbook() {
        return ebook;
    }

    public void setEbook(Ebook ebook) {
        this.ebook = ebook;
    }
}
