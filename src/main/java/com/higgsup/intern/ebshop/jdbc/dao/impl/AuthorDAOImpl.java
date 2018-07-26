package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.AuthorMapper;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final AuthorMapper authorMapper;


    public AuthorDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                         AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.authorMapper = authorMapper;
    }


    @Override
    public Author findbyId(Long id) {
    return null;
    }

    @Override
    public List<Author> findTop5BestSellingAuthors() {
    return null;
    }

    @Override
    public void create(Author author) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(author);
        String sql = "insert into author(id, firstname, lastname , year_of_birth , description, website ,organization)" +
                " values(:id, :firstName, :lastName , :yearOfBirth, :description, :website, :organization);";
               namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Long id) {

    }
}
