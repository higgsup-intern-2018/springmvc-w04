package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.AuthorDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.AuthorMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.model.Author;
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
public class AuthorDAOImpl implements AuthorDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final AuthorMapper authorMapper;
    private final EbookMapper ebookMapper;

    public AuthorDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, AuthorMapper authorMapper, EbookMapper ebookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.authorMapper = authorMapper;
        this.ebookMapper = ebookMapper;
    }

    @Override
    public Author findbyId(Long id) {
        try {
            SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
            String sql = "select * from author where id = :id;";
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
        return null;
    }

    @Override
    public void create(Author author) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Ebook> getTop3BooksOfAuthor(Long id) {
        String sql = "select ebook.* from ebook, author,order_details " +
                "where author.id = "+id+" "+
                "and author.id = ebook.author_id " +
                "and ebook.id = order_details.ebook_id " +
                "group by order_details.ebook_id " +
                "order by sum(order_details.quantity) desc " +
                "limit 3;";
        return jdbcTemplate.query(sql, ebookMapper);
    }

    @Override
    public Integer getBookCount(Long id) {
    SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
       String sql = " select count(ebook.author_id) as booksAuthor " +
               "from ebook " +
               "JOIN author ON ebook.author_id = author.id " +
               "where author.id = :id;";
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource,Integer.class);
    }
}
