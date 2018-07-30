package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EbookOrderMapper implements RowMapper<EbookOrderDTO>{
    @Override
    public EbookOrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        EbookOrderDTO ebookOrderDTO = new EbookOrderDTO();
        ebookOrderDTO.setTitle(rs.getString("ebook.title"));
        ebookOrderDTO.setAuthorFirstName(rs.getString("author.firstname"));
        ebookOrderDTO.setAuthorLastName(rs.getString("author.lastname"));
        ebookOrderDTO.setPublisherName(rs.getString("publisher.name"));
        ebookOrderDTO.setPrice(rs.getDouble("ebook.price"));
        ebookOrderDTO.setCopiesSold(rs.getInt("copies_sold"));
        return ebookOrderDTO;
    }
}
