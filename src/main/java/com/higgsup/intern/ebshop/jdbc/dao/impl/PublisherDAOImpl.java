package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.PublisherMapper;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.PersonMapper;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PublisherMapper publisherMapper;
    private final EbookMapper ebookMapper;

    public PublisherDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, PublisherMapper publisherMapper, EbookMapper ebookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.publisherMapper = publisherMapper;
        this.ebookMapper = ebookMapper;
    }

    @Override
    public void create(Publisher publisher) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(publisher);
        String sql = "insert into publisher (name, website, founder, founded_year, address) values (:name, :website, :founder, :foundedYear, :address);";
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String sql = "delete from publisher where id = :id;";
        namedParameterJdbcTemplate.update(sql, parameterSource);
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

    @Override
    public List<Publisher> findTop5BestSellingPublishers() {
        String sql = " select publisher.*, sum(order_details.quantity) countOfBook " +
                " from publisher " +
                " join ebook on publisher.id = ebook.publisher_id " +
                " join order_details on ebook.id = order_details.ebook_id " +
                " group by publisher.id " +
                " order by countOfBook DESC " +
                " limit 5;";
        return jdbcTemplate.query(sql, publisherMapper);
    }


    @Override
    public void update(Publisher publisher) {
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(publisher);
        String sql = " update publisher set  name = :name , website= :website , founder = :founder, founded_year = :foundedYear, address = :address " +
                " where id = :id ; ";
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public Integer countBookOfPublisher(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String sql = " select  count(publisher_id)" +
                " from ebook " +
                " left join publisher on publisher.id = ebook.publisher_id " +
                " where publisher.id = :id ";
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public List<Ebook> top5BookOfPublisher(Long id) {
        String sql = " select  ebook.*  " +
                " from ebook " +
                " left join publisher ON ebook.publisher_id = publisher.id" +
                " left join order_details On ebook.id = order_details.ebook_id" +
                " where publisher.id = " + id +
                " group by ebook_id" +
                " order by count(ebook_id) desc " +
                " limit 5;";
        return jdbcTemplate.query(sql, ebookMapper);
    }

}
