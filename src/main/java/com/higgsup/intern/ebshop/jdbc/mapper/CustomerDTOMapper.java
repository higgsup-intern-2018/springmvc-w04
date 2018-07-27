package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerDTOMapper implements RowMapper<CustomerDTO>{

    @Override
    public CustomerDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(resultSet.getLong("id"));
        customerDTO.setFirstName(resultSet.getString("firstname"));
        customerDTO.setLastName(resultSet.getString("lastname"));
        customerDTO.setEmail(resultSet.getString("email"));
        customerDTO.setPhone(resultSet.getString("phone"));
        customerDTO.setAddress(resultSet.getString("address"));
        customerDTO.setTotalPrice(resultSet.getDouble("totalPrice"));
        customerDTO.setCountOfBooks(resultSet.getInt("countOfBooks"));
        return customerDTO;
    }
}
