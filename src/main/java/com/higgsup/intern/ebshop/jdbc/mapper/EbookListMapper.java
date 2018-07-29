package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.dto.EbookListDTO;
import org.springframework.stereotype.Component;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class EbookListMapper implements RowMapper<EbookListDTO> {
    @Override
    public EbookListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        EbookListDTO ebookListDTO = new EbookListDTO();


        return ebookListDTO;
    }
}
