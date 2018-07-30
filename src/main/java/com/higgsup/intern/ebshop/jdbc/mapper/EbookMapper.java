package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EbookMapper implements RowMapper<Ebook> {
    @Override
    public Ebook mapRow(ResultSet rs, int i) throws SQLException {
        Ebook ebook = new Ebook();
        ebook.setId(rs.getLong("id"));
        ebook.setIsbn(rs.getString("isbn"));
        ebook.setTitle(rs.getString("title"));
        ebook.setDescription(rs.getString("description"));
        ebook.setAuthorId(rs.getLong("author_id"));
        ebook.setPublisherId(rs.getLong("publisher_id"));
        ebook.setPublicationDate(rs.getDate("publication_date"));
        ebook.setPages(rs.getInt("pages"));
        ebook.setPrice(rs.getDouble("price"));
        ebook.setQuantity(rs.getInt("quantity"));
        ebook.setDeleted(rs.getBoolean("deleted"));
        return ebook;
    }
}
