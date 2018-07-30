package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.jdbc.model.OrderDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderDetailsMapper implements RowMapper<OrderDetails> {

    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(rs.getLong("id"));
        orderDetails.setOrderId(rs.getLong("order_id"));
        orderDetails.setEbookId(rs.getLong("ebook_id"));
        orderDetails.setQuantity(rs.getInt("quantity"));
        return orderDetails;
    }
}
