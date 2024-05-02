package org.reelskill.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reelskill.data.mapper.DeckMapper;
import org.reelskill.models.Deck;
import org.reelskill.models.Role;
import org.reelskill.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserJdbcTemplateRepository repository;

    static boolean hasRun = false;

    @BeforeEach
    void  setup() {
        if (!hasRun)  {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void shouldFindUserById() {
        User expected = new User(2, "user2@example.com", "user2", "password2", Role.USER);
        User actual = repository.findById(2);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindUserById() {
        User actual = repository.findById(200);
        assertNull(actual);
    }

    @Test
    void shouldAuthenticateUser() {
        User expected = new User(2, "user2@example.com", "user2", "password2", Role.USER);
        expected.setDeckList(getDecksForUser(2)); // Manually set the deck list for the expected user

        User expectedAdmin = new User(3, "admin@example.com", "admin", "adminpassword", Role.ADMIN);
        expectedAdmin.setDeckList(getDecksForUser(3)); // Manually set the deck list for the expected admin user

        User actual = repository.authenticateUser("user2", "password2");
        User actualAdmin = repository.authenticateUser("admin",  "adminpassword");

        assertEquals(expected, actual);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    void shouldNotAuthenticateUser() {
        User actual = repository.authenticateUser("user2", "password");
        assertNull(actual);
    }

    @Test
    void shouldGetUserEmailAddress() {
        String expected = "user2@example.com";
        String actual = repository.getUserEmailAddress("user2");

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotGetUserEmailAddress() {
        String nonExistingUsername = "non_existing_user";
        String actual = repository.getUserEmailAddress(nonExistingUsername);

        assertNull(actual);
    }

    @Test
    void shouldCreateUser() {
        User user = new User(4, "user3@example.com", "user3", "password3", Role.USER);
        User expected = new User(4, "user3@example.com", "user3", "password3", Role.USER);

        User actual = repository.createUser(user);

        assertEquals(expected, actual);
    }

    // Helper method to retrieve decks for a user
    private List<Deck> getDecksForUser(int userId) {
        String sql = """
            select deck_id, user_id, deck_name, created_at
            from decks
            where user_id = ?;
            """;

        return jdbcTemplate.query(sql, new DeckMapper(), userId);
    }
}