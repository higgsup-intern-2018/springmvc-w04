package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.AuthorMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.PublisherMapper;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
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
    private final PublisherMapper publisherMapper;
    private final AuthorMapper authorMapper;

    public EbookDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, EbookMapper ebookMapper, PublisherMapper publisherMapper, AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.ebookMapper = ebookMapper;
        this.publisherMapper = publisherMapper;
        this.authorMapper = authorMapper;
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
    public Publisher getPublisherByEbookId(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = "select publisher.*, ebook.* from ebook\n" +
                "join publisher\n" +
                "on ebook.publisher_id = publisher.id\n" +
                "where ebook.id = :id;";
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, publisherMapper);
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
    @Override
    public Author infoOfAuthor(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = " select author.*" +
                " from ebook" +
                " left join author on ebook.author_id = author.id" +
                " where ebook.id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql,paramSource,authorMapper);
    }

}
