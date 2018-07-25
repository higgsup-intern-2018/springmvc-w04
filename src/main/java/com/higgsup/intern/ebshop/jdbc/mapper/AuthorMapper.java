package com.higgsup.intern.ebshop.jdbc.mapper;

import com.higgsup.intern.ebshop.jdbc.model.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int i) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("firstname"));
        author.setLastName(rs.getString("lastname"));
        author.setYearOfBirth(rs.getInt("year_of_birth"));
        author.setDescription(rs.getString("description"));
        author.setWebsite(rs.getString("website"));
        author.setOrganization(rs.getString("organization"));
        return author;
    }
}
