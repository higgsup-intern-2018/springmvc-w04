package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.jdbc.model.OrderDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderDetailMapper  implements RowMapper<OrderDetails>{
    @Override
    public OrderDetails mapRow(ResultSet rs, int i) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(rs.getLong("id"));
        orderDetails.setOrderId(rs.getLong("order_id"));
        orderDetails.setOrderId(rs.getLong("ebook_id"));
        orderDetails.setOrderId(rs.getLong("quantity"));
        return orderDetails;
    }
}
