package com.higgsup.intern.ebshop.jpa.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Orders orders;

    @ManyToOne
    @JoinColumn
    private Ebook ebook;

    @Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
