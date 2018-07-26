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

    public EbookDAOImpl(JdbcTemplate jdbcTemplate,
                         NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                         EbookMapper ebookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.ebookMapper = ebookMapper;
    }
    @Override
    public List<Ebook> find(String name, Long authorId, Long publisherId, Long priceFrom, Long priceTo, String isbn) {
        return null;
    }

    @Override
    public List<Ebook> findTop10BestSellerEbooks() {
        return null;
    }

    @Override
    public Ebook findById(String isbn) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("isbn", isbn);
            String sql = "SELECT * FROM ebook WHERE isbn = :isbn;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void create(Ebook ebook) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(ebook);
        String sql = "INSERT INTO ebook(isbn, title, description, author_id, publisher_id, publication_date, pages, price, quantity, deleted) " +
                "VALUES (:isbn, :title, :description, :authorId, :publisherId, :publicationDate, :pages, :price, :quantity, :deleted);";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public void update(Ebook ebook) {

    }

    @Override
    public void updateAddedEbook(Ebook ebook) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(ebook);
        String sql = "UPDATE ebook " +
                "SET title = :title, description = :description, author_id = :authorId, publisher_id = :publisherId, " +
                "publication_date = :publicationDate, pages = :pages, price = :price, deleted = :deleted, quantity = :quantity " +
                "WHERE isbn = :isbn;";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public void delete(Long id) {

    }
}
