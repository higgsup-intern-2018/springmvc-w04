package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.PublisherMapper;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public Publisher findbyId(Long id) {
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
        return jdbcTemplate.query(sql ,publisherMapper);
    }

    @Override
    public void create(Publisher publisher) {

    }

    @Override
    public void update(Publisher publisher) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Integer countBookOfPublisher(Long id) {
      return null;
    }

    @Override
    public List<Ebook> top5BookOfPublisher(Long id) {
       return null;
    }

}
