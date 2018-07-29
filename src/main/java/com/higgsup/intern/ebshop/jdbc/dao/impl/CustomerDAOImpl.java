package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.CustomerDTOMapper;
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
    private final CustomerDTOMapper customerDTOMapper;
    private final CustomerMapper customerMapper;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           CustomerDTOMapper customerDTOMapper,
                           CustomerMapper customerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.customerDTOMapper = customerDTOMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findTop5HighestOrderPriceCustomers() {    //top 5 KH co tong gia tri hoa don cao nhat
        String sql = "select customer.*, " +
                        "sum(ebook.price * order_details.quantity) TotalPrice, " +
                        "sum(order_details.quantity) countOfBooks " +
                    "from customer " +
                    "join orders on customer.id = orders.customer_id " +
                    "join order_details on orders.id = order_details.order_id " +
                    "join ebook on order_details.ebook_id = ebook.id " +
                    "group by customer.email  " +
                    "order by TotalPrice DESC " +
                    "limit 5;";
        return namedParameterJdbcTemplate.query(sql,customerDTOMapper);
    }

    @Override
    public Integer countBook(Long id) {
        return null;
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
}
