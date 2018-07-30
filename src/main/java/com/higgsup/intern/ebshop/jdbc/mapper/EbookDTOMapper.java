package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EbookDTOMapper implements RowMapper<EbookDTO> {

    @Override
    public EbookDTO mapRow(ResultSet rs, int i) throws SQLException {
        EbookDTO ebookDTO = new EbookDTO();
        ebookDTO.setId(rs.getLong("id"));
        ebookDTO.setIsbn(rs.getString("isbn"));
        ebookDTO.setTitle(rs.getString("title"));
        ebookDTO.setDescription(rs.getString("description"));
        ebookDTO.setPublicationDate(rs.getDate("publication_date"));
        ebookDTO.setPages(rs.getInt("pages"));
        ebookDTO.setPrice(rs.getDouble("price"));
        ebookDTO.setQuantity(rs.getInt("quantity"));
        return ebookDTO;
    }
}
