package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.AuthorMapper;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final AuthorMapper authorMapper;

    public AuthorDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.authorMapper = authorMapper;
    }

    @Override
    public Author findById(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "SELECT * FROM author WHERE id = :id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, authorMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Author> findTop5BestSellingAuthors() {
        return null;
    }

    @Override
    public void create(Author author) {

    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = "DELETE FROM author WHERE id = :id;";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }
}
