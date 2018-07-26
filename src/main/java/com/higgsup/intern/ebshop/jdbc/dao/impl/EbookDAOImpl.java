package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.EbookOrderMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EbookDAOImpl implements EbookDAO {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final EbookOrderMapper ebookOrderMapper;

    public EbookDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                        EbookOrderMapper ebookOrderMapper)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.ebookOrderMapper = ebookOrderMapper;
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
