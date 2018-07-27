package com.higgsup.intern.ebshop.jdbc.dao.impl;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.AuthorMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookMapper;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbookDAOImpl implements EbookDAO{
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final EbookMapper ebookMapper;
    private final AuthorMapper authorMapper;

    public EbookDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                        EbookMapper ebookMapper, AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.ebookMapper = ebookMapper;
        this.authorMapper = authorMapper;
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
    public void create(Ebook ebook) {

    }

    @Override
    public void update(Ebook ebook) {

    }

    @Override
    public void delete(Long id) {

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
