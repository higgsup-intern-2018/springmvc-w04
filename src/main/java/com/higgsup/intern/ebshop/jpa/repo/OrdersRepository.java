package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {

    @Query("select c from Orders o join o.customer c where o.id = :id")
    Customer getCustomerByOrdersId(@Param("id") Long id);

    @Query("select e from OrderDetails od join od.orders o join od.ebook e where o.id = :id")
    List<Ebook> getEbookByOrdersId(@Param("id") Long id);

    @Query("select od.quantity from OrderDetails od join od.ebook e where e.id = :id")
    Integer getQuantityByEbookId(@Param("id") Long id);

    @Query("select sum(e.price * od.quantity) from OrderDetails od join od.ebook e join od.orders o where o.id = :id")
    Double totalPrice(@Param("id") Long id);
}
