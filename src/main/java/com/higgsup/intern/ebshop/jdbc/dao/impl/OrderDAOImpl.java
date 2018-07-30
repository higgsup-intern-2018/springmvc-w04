package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.jdbc.dao.OrderDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.*;
import com.higgsup.intern.ebshop.jdbc.model.Orders;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final OrderExportMapper orderExportMapper;
    private final EbookOrderMapper ebookOrderMapper;
    private final OrderMapper orderMapper;

    public OrderDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, OrderExportMapper orderExportMapper, EbookOrderMapper ebookOrderMapper, OrderMapper orderMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.orderExportMapper = orderExportMapper;
        this.ebookOrderMapper = ebookOrderMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderExportDTO exportOrder(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("order_id", id);
            String sql = "select orders.id, customer.firstname, customer.lastname, customer.email, customer.phone, sum(ebook.price * order_details.quantity) total_price  " +
                    "from orders JOIN order_details ON orders.id = order_details.order_id " +
                    "join customer ON orders.customer_id = customer.id " +
                    "JOIN ebook ON order_details.ebook_id = ebook.id " +
                    "where orders.id = :order_id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, orderExportMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<EbookOrderDTO> findByOrderId(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("order_id", id);
        String sql = "SELECT ebook.title, author.firstname, author.lastname, publisher.`name`, ebook.price, order_details.quantity as copies_sold " +
                "FROM orders JOIN order_details ON orders.id = order_details.order_id " +
                "JOIN ebook ON order_details.ebook_id = ebook.id " +
                "JOIN author ON ebook.author_id = author.id " +
                "JOIN publisher ON ebook.publisher_id = publisher.id " +
                "WHERE orders.id = :order_id;";
        return namedParameterJdbcTemplate.query(sql, paramSource, ebookOrderMapper);
    }

    @Override
    public void createOrder(Orders orders) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(orders);
        String sql = "insert into orders(id, customer_id, created_date) values (:id, :customerId, :createdDate);";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public Long getId(String date) {
        SqlParameterSource paramSource = new MapSqlParameterSource("created", date);
        String sql = "select id from orders where created_date = :created;";
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, Long.class);
    }
}
