package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import com.higgsup.intern.ebshop.jdbc.dao.OrderDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public OrderDAOImpl(JdbcTemplate jdbcTemplate,
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                        OrderExportMapper orderExportMapper,
                        EbookOrderMapper ebookOrderMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.orderExportMapper = orderExportMapper;
        this.ebookOrderMapper = ebookOrderMapper;
    }

    @Override
    public OrderExportDTO exportOrder(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("order_id", id);
            String sql = "select orders.id, customer.firstname, customer.lastname, customer.email, customer.phone from orders join customer " +
                    "ON orders.customer_id = customer.id where orders.id = :order_id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, orderExportMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public List<EbookOrderDTO> findByOrderId(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("order_id", id);
        String sql = "SELECT ebook.title, author.firstname, author.lastname, publisher.`name`, ebook.price, order_details.quantity as'copied_sold' " +
                "FROM orders JOIN order_details ON orders.id = order_details.order_id " +
                "JOIN ebook ON order_details.ebook_id = ebook.id " +
                "JOIN author ON ebook.author_id = author.id " +
                "JOIN publisher ON ebook.publisher_id = publisher.id " +
                "WHERE orders.id = :order_id;";
        return namedParameterJdbcTemplate.query(sql, paramSource, ebookOrderMapper);
    }
}
