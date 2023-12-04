package uz.pdp.repository;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.domain.User;
import uz.pdp.mapper.UserRowMapper;

import java.util.Map;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(@NonNull final User user) {
        jdbcTemplate.update(
                "INSERT INTO users(firstName, lastName, age) VALUES (?, ? ,?)",
                user.getFirstName(),
                user.getLastName(),
                user.getAge()
        );
    }

    public void saveWithParams(@NonNull final User user) {
        jdbcTemplate.update(
                "INSERT INTO users(firstName, lastName, age) VALUES (?, ? ,?)",
                ps -> {
                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    ps.setInt(3, user.getAge());
                }
        );
    }

    public User get(final long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new UserRowMapper(), id);
    }

    public User getByFirstName(final String firstName) {
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE firstName=:firstName",
                Map.of("firstName", firstName),
                new UserRowMapper()
        );
    }
}
