package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.OrderDetailsDAO;
import com.higgsup.intern.ebshop.jdbc.model.OrderDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderDetailsDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void createOrderDetails(OrderDetails orderDetails) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(orderDetails);
        String sql = "insert into order_details(id, order_id, ebook_id, quantity) values (:id, :orderId, :ebookId, :quantity);";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }
}
