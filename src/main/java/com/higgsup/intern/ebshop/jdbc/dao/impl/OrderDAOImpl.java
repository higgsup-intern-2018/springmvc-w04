package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.OrderDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.OrderMapper;
import com.higgsup.intern.ebshop.jdbc.model.Orders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final OrderMapper orderMapper;

    public OrderDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, OrderMapper orderMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.orderMapper = orderMapper;
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
