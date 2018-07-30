package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.CustomerMapper;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CustomerMapper customerMapper;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, CustomerMapper customerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createCustomer(Customer customer) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(customer);
        String sql = "insert into customer(id, firstname, lastname, email, phone, address) values (:id, :firstName, :lastName, :email, :phone, :address);";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public Long getId(String email) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("email", email);
            String sql = "select id from customer where email = :email;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, Long.class);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
