package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.OrderDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.OrderMapper;
import com.higgsup.intern.ebshop.jdbc.model.Orders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
}
