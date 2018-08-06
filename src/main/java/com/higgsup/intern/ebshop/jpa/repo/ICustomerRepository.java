package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    @Query( "SELECT c, " +
            "SUM(od.quantity) AS quantity, " +
            "SUM(e.price * quantity) AS totalPriceOfOrder " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "JOIN od.ebook e " +
            "GROUP BY c.email " +
            "ORDER BY totalPriceOfOrder DESC ")
    List<CustomerDTO> findTop5HighestOrderPriceCustomers();


    @Query( "SELECT c, " +
            "SUM(od.quantity) AS totalQuantity, " +
            "SUM(e.price * totalQuantity) AS totalPrice FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "JOIN od.ebook e " +
            "GROUP BY c.id " +
            "ORDER BY totalQuantity DESC ")
    List<Customer> findTop5BestBuyCustomers();


    @Query("SELECT c.id FROM Customer c where c.email = :email")
    Long getId(String email);
}
