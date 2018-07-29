package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.dto.EbookListDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.AuthorMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookOrderMapper;
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
    private final EbookOrderMapper ebookOrderMapper;

    public EbookDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, EbookMapper ebookMapper, PublisherMapper publisherMapper, AuthorMapper authorMapper, EbookOrderMapper ebookOrderMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.ebookMapper = ebookMapper;
        this.publisherMapper = publisherMapper;
        this.authorMapper = authorMapper;
        this.ebookOrderMapper = ebookOrderMapper;
    }

    @Override
    public Ebook findById(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "select * from ebook where id = :id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<EbookListDTO> find(String title, Long authorId, Long publisherId,
                                   Long priceFrom, Long priceTo, String isbn) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("title", title)
                    .addValue("authorId", authorId)
                    .addValue("publisherId", publisherId)
                    .addValue("priceFrom", priceFrom)
                    .addValue("priceTo", priceTo)
                    .addValue("isbn", isbn);
            String condition= " ";
            if (title != null){
                condition = "title = :title ORDER BY title ASC ;";
            }
            if (authorId != null){
                condition = "authorId = :authorId ORDER BY title ASC ; ";
            }
            if (isbn != null){
                condition = " ";
            }
            if (priceFrom != null && priceTo != null){

            }
            String sql = "SELECT * FROM ebook WHERE " + condition;


            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }















        return null;
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
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql ="UPDATE ebook SET deleted = TRUE where id = :id";
        namedParameterJdbcTemplate.update(sql,paramSource);
    }

    @Override
    public Ebook findByTitle(String title) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("title", title);
            String sql = "SELECT * FROM ebook WHERE title = :title ORDER BY title ASC ;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Ebook findByAuthorId(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "SELECT * FROM ebook WHERE id = :id ORDER BY title ASC ;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Ebook findByRangeOfPrice(Double priceFrom, Double priceTo) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource().addValue("priceFrom", priceFrom)
                    .addValue("priceTo", priceTo);
            String sql = "SELECT * FROM ebook WHERE price BETWEEN priceFrom AND priceTo ORDER BY price DESC;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, ebookMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Publisher getPublisherByEbookId(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = "select publisher.*, ebook.* from ebook " +
                "join publisher " +
                "on ebook.publisher_id = publisher.id " +
                "where ebook.id = :id;";
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, publisherMapper);
    }

    @Override
    public List<EbookOrderDTO> findTop10BestSellerEbooks() {
        String sql = "SELECT ebook.id, ebook.title, author.firstname, author.lastname, publisher.`name`, ebook.price, SUM(order_details.quantity) copies_sold " +
                "FROM order_details JOIN ebook ON order_details.ebook_id = ebook.id " +
                "JOIN author ON ebook.author_id = author.id " +
                "JOIN publisher ON ebook.publisher_id = publisher.id " +
                "GROUP BY(ebook_id) " +
                "ORDER BY SUM(order_details.quantity) DESC " +
                "LIMIT 10;";
        return jdbcTemplate.query(sql, ebookOrderMapper);
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

    @Override
    public List<EbookOrderDTO> top10BestSeller() {
        String sql = "SELECT ebook.id, ebook.title, author.firstname, author.lastname, publisher.`name`, ebook.price, SUM(order_details.quantity) AS \"copies_sold\" " +
                "FROM order_details JOIN ebook ON order_details.ebook_id = ebook.id " +
                "JOIN author ON ebook.author_id = author.id " +
                "JOIN publisher ON ebook.publisher_id = publisher.id " +
                "GROUP BY(ebook_id) " +
                "ORDER BY SUM(order_details.quantity) DESC " +
                "LIMIT 10;";
        return jdbcTemplate.query(sql, ebookOrderMapper);
    }

}
