package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByEmail(String email);
    @Query( "SELECT c " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "JOIN od.ebook e " +
            "GROUP BY c.email " +
            "ORDER BY SUM (od.quantity) DESC ")
    List<Customer> findTop5HighestOrderPriceCustomers();

    @Query( "SELECT SUM(e.price * od.quantity) " +
            "FROM OrderDetails od " +
            "JOIN od.ebook e " +
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "WHERE c.id = :id ")
    Double totalPrice (@Param("id") Long id);

    @Query( "SELECT SUM(od.quantity) " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "WHERE c.id = :id ")
    Integer quantity (@Param("id") Long id);


    @Query( "SELECT c " +
            "FROM OrderDetails od " +
            "JOIN od.orders o " +
            "JOIN o.customer c " +
            "JOIN od.ebook e " +
            "GROUP BY c.id " +
            "ORDER BY SUM (od.quantity) DESC ")
    List<Customer> findTop5BestBuyCustomers();


    @Query("SELECT c.id FROM Customer c where c.email = :email")
    Long getId(@Param("email")String email);
}
