package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbookDAOImpl implements EbookDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final EbookMapper ebookMapper;

    public EbookDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, EbookMapper ebookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.ebookMapper = ebookMapper;
    }

    @Override
    public Ebook findById(Long id) {    // check if the book existed on db
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "select * from ebook where id = :id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Ebook ebook) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(ebook);
        String sql = "UPDATE ebook " +
                    "SET isbn = :isbn, " +
                        "title = :title, " +
                        "description = :description, " +
                        "author_id = :authorId, " +
                        "publisher_id = :publisherId, " +
                        "publication_date = :publicationDate, " +
                        "pages = :pages, " +
                        "price = :price, " +
                        "quantity = :quantity " +
                    "WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public Ebook findByIsbn(String isbn) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("isbn", isbn);
            String sql = "SELECT * FROM ebook WHERE isbn = :isbn;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
