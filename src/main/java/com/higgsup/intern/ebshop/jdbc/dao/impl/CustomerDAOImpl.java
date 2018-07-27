package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.CustomerMapper;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CustomerMapper customerMapper;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, CustomerMapper customerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<Customer> findTop5BestBuyCustomers() {
        String sql = "SELECT customer.*, sum(order_details.quantity) totalQuantity , sum(ebook.price * order_details.quantity) totalPrice FROM customer " +
                "JOIN orders ON customer.id = orders.customer_id " +
                "JOIN order_details ON orders.id = order_details.order_id " +
                "JOIN ebook ON order_details.ebook_id = ebook.id "+
                "GROUP BY customer.id " +
                "ORDER BY totalQuantity DESC " +
                "limit 5;";
        return jdbcTemplate.query(sql, customerMapper);
    }

    @Override
    public List<Customer> findTop5HighestOrderPriceCustomers() {
        return null;
    }
}
