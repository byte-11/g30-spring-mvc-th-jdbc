package uz.pdp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import uz.pdp.domain.User;
import uz.pdp.mapper.UserRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final SimpleJdbcCall simpleJdbcCall;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, SimpleJdbcCall simpleJdbcCall) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.simpleJdbcCall = simpleJdbcCall;
    }

    public void save(final User user) {
        jdbcTemplate.update("""
                        INSERT INTO users(firstName, lastName, age) 
                        VALUES (?, ? , ?)
                        """,
                user.getFirstname(),
                user.getLastname(),
                user.getAge());
    }

    public void saveWithParams(final User user) {
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update("""
                INSERT INTO users(firstName, lastName, age) 
                VALUES (:firstname, :lastname , :age)
                """, source);
    }

    public User saveAndReturnId(final User user) {
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement statement = con.prepareStatement("""
                    INSERT INTO users(firstName, lastName, age) 
                    VALUES (?, ? , ?)
                    """, new String[]{"id", "age"});
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setInt(3, user.getAge());
            return statement;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        keys.forEach((k, v) -> System.out.println(k + " - " + v));
        user.setId((Long) keys.get("id"));
        return user;
    }

    public void delete(final long id) {
        jdbcTemplate.update("DELETE FROM users where id = ?", id);
    }

    public List<User> getAll(int age) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE age > ?", new UserRowMapper(), age);
        return users;

    }

    public void saveWithSimpleJdbc(final User user){
        simpleJdbcInsert.withTableName("users")
                .usingGeneratedKeyColumns("id")
                .execute(new BeanPropertySqlParameterSource(user));
    }

    public void getUserByName(String name) {
        simpleJdbcCall.withProcedureName("");
    }
}
