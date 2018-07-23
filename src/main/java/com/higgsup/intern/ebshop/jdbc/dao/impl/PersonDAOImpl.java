package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.PersonDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.PersonMapper;
import com.higgsup.intern.ebshop.jdbc.model.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PersonMapper personMapper;

    public PersonDAOImpl(JdbcTemplate jdbcTemplate,
                         NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                         PersonMapper personMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.personMapper = personMapper;
    }

    @Override
    public Person findById(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "select * from person where id = :id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, personMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Person> findAll() {
        String sql = "select * from person;";
        return jdbcTemplate.query(sql, personMapper);
    }

    @Override
    public void create(Person person) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(person);
        String sql = "insert into person(first_name, last_name, age) values (:firstName, :lastName, :age);";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public void update(Person person) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(person);
        String sql = "UPDATE person " +
                "SET first_name = :firstName, last_name = :lastName, age = :age " +
                "WHERE id = :id;";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = "delete from person where id = :id;";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }
}
