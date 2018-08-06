package com.higgsup.intern.ebshop.jpa.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
public class OrderDetails {
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
    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
    @OneToOne
    @JoinColumn(name = "ebook_id")
    private Ebook ebook;
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
