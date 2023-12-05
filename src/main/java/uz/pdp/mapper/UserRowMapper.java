package uz.pdp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uz.pdp.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .firstname(rs.getString("firstname"))
                .lastname(rs.getString("lastname"))
                .age(rs.getInt("age"))
                .build();
    }
}
