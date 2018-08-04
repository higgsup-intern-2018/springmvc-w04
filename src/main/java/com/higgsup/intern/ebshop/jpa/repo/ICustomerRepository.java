package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    @Query( "SELECT customer.id, customer.firstname, customer.lastname, customer. email, customer.phone, customer.address, " +
            "SUM(ebook.price * order_details.quantity) AS totalPriceOfOrder, " +
            "SUM(order_details.quantity) AS quantity " +
            "FROM customer " +
            "JOIN orders on customer.id = orders.customer_id " +
            "JOIN order_details on orders.id = order_details.order_id " +
            "JOIN ebook on order_details.ebook_id = ebook.id " +
            "GROUP BY customer.email  " +
            "ORDER BY totalPriceOfOrder DESC ")
    List<CustomerDTO> findTop5HighestOrderPriceCustomers();

    //Todo: set limit

    @Query( "SELECT customer.id, customer.firstname, customer.lastname, customer. email, customer.phone, customer.address, " +
            "SUM(order_details.quantity) AS totalQuantity , " +
            "SUM(ebook.price * order_details.quantity) AS totalPrice FROM customer " +
            "JOIN orders ON customer.id = orders.customer_id " +
            "JOIN order_details ON orders.id = order_details.order_id " +
            "JOIN ebook ON order_details.ebook_id = ebook.id " +
            "GROUP BY customer.id " +
            "ORDER BY totalQuantity DESC ")
    List<Customer> findTop5BestBuyCustomers();

    //Todo: set limit

    @Query("SELECT id FROM customer where email = :email")
    Long getId(String email);
}
