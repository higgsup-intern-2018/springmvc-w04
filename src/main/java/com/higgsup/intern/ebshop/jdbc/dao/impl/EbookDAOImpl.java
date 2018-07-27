package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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

    @Override
    public void delete(Long id) {

    }

 @Override
    public Ebook findByIsbn(String isbn) {
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
    public void updateAddedEbook(Ebook ebook) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(ebook);
        String sql = "UPDATE ebook " +
                "SET title = :title, description = :description, author_id = :authorId, publisher_id = :publisherId, " +
                "publication_date = :publicationDate, pages = :pages, price = :price, deleted = :deleted, quantity = :quantity " +
                "WHERE isbn = :isbn;";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }


}
