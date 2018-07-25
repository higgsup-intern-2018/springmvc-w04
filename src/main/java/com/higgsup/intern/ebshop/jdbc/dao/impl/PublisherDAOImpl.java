package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.PersonMapper;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PersonMapper personMapper;

    public PublisherDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                            PersonMapper personMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.personMapper = personMapper;
    }

    public void delete(Long id)
    {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String sql = "delete from publisher where id = :id";
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
