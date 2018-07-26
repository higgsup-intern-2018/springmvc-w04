package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.dto.OrderExportDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderExportMapper implements RowMapper<OrderExportDTO> {
    @Override
    public OrderExportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderExportDTO orderExportDTO = new OrderExportDTO();
        orderExportDTO.setId(rs.getLong("orders.id"));
        orderExportDTO.setCustomerFirstName(rs.getString("customer.firstname"));
        orderExportDTO.setCustomerLastName(rs.getString("customer.lastname"));
        orderExportDTO.setEmail(rs.getString("customer.email"));
        orderExportDTO.setPhone(rs.getString("customer.phone"));
        return orderExportDTO;
    }
}
