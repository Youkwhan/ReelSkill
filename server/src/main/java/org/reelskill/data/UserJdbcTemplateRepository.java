package org.reelskill.data;

import org.reelskill.data.mapper.DeckMapper;
import org.reelskill.data.mapper.UserMapper;
import org.reelskill.models.Deck;
import org.reelskill.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(int userId) {
        String sql = """
                select user_id, username, password, email_address, role
                from user
                where user_id = ?;
                """;
        return jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User authenticateUser(String username, String password) {
        String sql = """
                select user_id, username, password, email_address, role
                from user
                where username = ? and password = ?;
                """;
        User user = jdbcTemplate.query(sql, new UserMapper(), username, password).stream()
                .findFirst()
                .orElse(null);

        if (user != null) {
            assignDeckToUser(user);
        }
        return user;
    }

    @Override
    public String getUserEmailAddress(String username) {
        String sql = """
                select email_address
                from user
                where username = ?;
                """;
        return jdbcTemplate.queryForObject(sql, String.class, username);
    }

    @Transactional
    @Override
    public User createUser(User user) {
        String sql = """
                insert into user (username, password, email_address, role)
                values (?, ?, ?, ?);
                """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update((conn) ->  {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmailAddress());
            statement.setString(4, user.getRole().toString());
            return  statement;
        },  keyHolder);

        if (rowsAffected > 0) {
            user.setUserId(keyHolder.getKey().intValue());
            return user;
        }

        return null;
    }

    private void assignDeckToUser(User user) {
        String sql = """
                select deck_id, deck_name, created_at
                from decks
                where user_id = ?;
                """;

        List<Deck> decks = jdbcTemplate.query(sql, new DeckMapper(), user.getUserId());
        user.setDeckList(decks);

    }
}
