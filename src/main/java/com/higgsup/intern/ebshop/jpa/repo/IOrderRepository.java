package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IOrderRepository extends CrudRepository<Orders, Long> {

    @Query( "SELECT e " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN od.ebook e " +
            "WHERE o.id = :id")
    List<Ebook> getEbookByOrdersId(@Param("id") Long id);


    @Query( "SELECT c " +
            "FROM Orders o " +
            "JOIN o.customer c " +
            "WHERE o.id = :id")
    Customer getCustomerByOrdersId(@Param("id") Long id);

    @Query("SELECT o FROM Orders o WHERE o.createdDate = :date")
    Long getId(@Param("date") String date);

    @Query( "SELECT od.quantity " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN od.ebook e " +
            "WHERE e.id = :ebookId " +
            "AND o.id = :ordersId")
    Integer getQuantityByEbookId(@Param("ebookId") Long ebookId, @Param("ordersId") Long ordersId);

    @Query( "SELECT SUM(e.price * od.quantity) " +
            "FROM OrderDetails od " +
            "JOIN od.orders o "+
            "JOIN od.ebook e " +
            "WHERE o.id = :id")
    Double totalPrice(@Param("id")Long id);
}
