package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
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
public class AuthorDAOImpl implements AuthorDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final AuthorMapper authorMapper;
    private final EbookMapper ebookMapper;
    private final PublisherMapper publisherMapper;

    public AuthorDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, AuthorMapper authorMapper, EbookMapper ebookMapper, PublisherMapper publisherMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.authorMapper = authorMapper;
        this.ebookMapper = ebookMapper;
        this.publisherMapper = publisherMapper;
    }


    @Override
    public Integer countEbooksOfAnAuthor(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("author_id", id);
            String sql = "SELECT COUNT(ebook.id) " +
                    "FROM ebook INNER JOIN author ON ebook.author_id = author.id " +
                    "WHERE author.id = :author_id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Author findById(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "select author.*, count(ebook.author_id) countOfBooks " +
                    "from author " +
                    "left join ebook on ebook.author_id = author.id " +
                    "where author.id = :id;";
            return namedParameterJdbcTemplate.queryForObject(sql, paramSource, authorMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Author author) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(author);
        String sql = "UPDATE author " +
                "SET firstname = :firstName, " +
                "lastname = :lastName, " +
                "year_of_birth = :yearOfBirth, " +
                "description = :description, " +
                "website = :website, " +
                "organization = :organization " +
                "WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public List<Author> findTop5BestSellingAuthors() {
        String sql = "select author.*, " +
                "sum(order_details.quantity) countOfBooks " +
                "from author " +
                "join ebook " +
                "on author.id = ebook.author_id " +
                "join order_details " +
                "on ebook.id = order_details.ebook_id " +
                "group by author.id " +
                "order by countOfBooks DESC " +
                "limit 5;";
        return jdbcTemplate.query(sql, authorMapper);
    }

    @Override
    public void create(Author author) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(author);
        String sql = "insert into author(id, firstname, lastname, year_of_birth, description, website, organization)" +
                " values(:id, :firstName, :lastName , :yearOfBirth, :description, :website, :organization);";
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = "DELETE FROM author WHERE id = :id;";

        if (countEbooksOfAnAuthor(id) == 0) {
            namedParameterJdbcTemplate.update(sql, paramSource);
        }
    }

    @Override
    public List<Ebook> getTop3BooksOfAuthor(Long id) {
        String sql = "select ebook.* from ebook, author,order_details " +
                "where author.id = " + id + " " +
                "and author.id = ebook.author_id " +
                "and ebook.id = order_details.ebook_id " +
                "group by order_details.ebook_id " +
                "order by sum(order_details.quantity) desc " +
                "limit 3;";
        return jdbcTemplate.query(sql, ebookMapper);
    }

    @Override
    public Integer getBookCount(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String sql = " select count(ebook.author_id) as booksAuthor " +
                "from ebook " +
                "JOIN author ON ebook.author_id = author.id " +
                "where author.id = :id;";
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    public Publisher getPublisherByEbookId(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        String sql = "select publisher.*, ebook.* from ebook " +
                "join publisher " +
                "on ebook.publisher_id = publisher.id " +
                "where ebook.id = :id;";
        return namedParameterJdbcTemplate.queryForObject(sql, paramSource, publisherMapper);
    }
}
