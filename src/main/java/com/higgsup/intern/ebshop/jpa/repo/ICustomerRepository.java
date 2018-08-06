package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer ,Long> {

    @Query("SELECT c.id FROM Customer c where c.email = :email")
    Long getId(@Param("email") String email);

    //Top 5 khach hang co tong hoa don cao nhat
    @Query( "SELECT c, " +
            "SUM(od.quantity) AS quantity," +
            "SUM( e.price * quantity ) AS totalPriceOfOrder " + //error
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN od.customer c  " +
            "JOIN od.ebook e " +
            "GROUP BY c.email  " +
            "ORDER BY totalPriceOfOrder DESC ")
    List<CustomerDTO> findTop5HighestOrderPriceCustomers();

    //Top 5 khach hang mua nhieu sach nhat
    @Query( "SELECT c, " +
            "SUM(od.quantity) AS totalQuantity, " +
            "SUM ( e.price * totalQuantity ) AS totalPrice FROM OrderDetails od " +//error
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "JOIN od.ebook e " +
            "GROUP BY c.id " +
            "ORDER BY totalQuantity DESC ")
    List<Customer> findTop5BestBuyCustomers();
}
