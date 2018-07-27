package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PublisherMapper implements RowMapper<Publisher> {
    @Override
    public Publisher mapRow(ResultSet rs, int i) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(rs.getLong("id"));
        publisher.setName(rs.getString("name"));
        publisher.setWebsite(rs.getString("website"));
        publisher.setFounder(rs.getString("founder"));
        publisher.setFoundedYear(rs.getInt("founded_year"));
        publisher.setAddress(rs.getString("address"));
        publisher.setCountOfBook(rs.getInt("countOfBook"));
        return publisher;
    }
}
