package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.jpa.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Orders, Long> {

    @Query( "SELECT ebook.title, author.firstname, author.lastname, publisher.name, ebook.price, order_details.quantity AS copies_sold " +
            "FROM orders JOIN order_details ON orders.id = order_details.order_id " +
            "JOIN ebook ON order_details.ebook_id = ebook.id " +
            "JOIN author ON ebook.author_id = author.id " +
            "JOIN publisher ON ebook.publisher_id = publisher.id " +
            "WHERE orders.id = :id")
    List<EbookOrderDTO> findByOrderId(Long id);

    @Query( "SELECT orders.id, customer.firstname, customer.lastname, customer.email, customer.phone, " +
            "SUM(ebook.price * order_details.quantity) AS total_price  " +
            "FROM orders JOIN order_details ON orders.id = order_details.order_id " +
            "JOIN customer ON orders.customer_id = customer.id " +
            "JOIN ebook ON order_details.ebook_id = ebook.id " +
            "WHERE orders.id = :id")
    OrderExportDTO exportOrder(Long id);

    @Query("select id from orders where created_date = :date")
    Long getId(String date);
}
