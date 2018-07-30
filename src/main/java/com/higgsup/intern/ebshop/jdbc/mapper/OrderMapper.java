package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.jdbc.model.Orders;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderMapper implements RowMapper<Orders> {

    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders order = new Orders();
        order.setId(rs.getLong("id"));
        order.setCustomerId(rs.getLong("customer_id"));
        order.setCreatedDate(rs.getDate("created_date"));
        return order;
    }
}
