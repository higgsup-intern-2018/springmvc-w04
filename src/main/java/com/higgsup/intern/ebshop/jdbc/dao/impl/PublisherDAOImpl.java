package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.PersonMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.PublisherMapper;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PublisherMapper publisherMapper;

    public PublisherDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                            PublisherMapper publisherMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public Publisher findById(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "select * from publisher where id = :id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, publisherMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public void delete(Long id)
    {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String sql = "delete from publisher where id = :id";
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public Integer countBook(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("publisher_id", id);
        String sql = "select count(*) from publisher join ebook ON publisher.id = ebook.publisher_id where publisher.id = :publisher_id;";
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
        return count;
    }
}
