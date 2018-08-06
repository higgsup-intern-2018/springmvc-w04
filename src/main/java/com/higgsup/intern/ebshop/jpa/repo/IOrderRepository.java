package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Orders, Long> {

   @Query("select o.id from Orders o where o.createdDate = :date")
   Long getId(@Param("date") String date);

   @Query( "SELECT e.title, a.firstname, a.lastname, p.name, e.price, od.quantity AS copies_sold " +
            "FROM OrderDetails od " +
            "JOIN od.orders o  " +
            "JOIN od.ebook e " +
            "JOIN e.author a " +
            "JOIN e.publisher p " +
            "WHERE o.id = :id")
    List<EbookOrderDTO> findByOrderId(@Param("id") Long id);

    @Query( "SELECT o.id, c.firstname, c.lastname, c.email, c.phone, " +
            "SUM(e.price * od.quantity) AS total_price  " + //error
            "FROM OrderDetails od " +
            "JOIN od.orders o  " +
            "JOIN o.customer c " +
            "JOIN od.ebook e  " +
            "WHERE o.id = :id")
    OrderExportDTO exportOrder(@Param("id") Long id);


}
