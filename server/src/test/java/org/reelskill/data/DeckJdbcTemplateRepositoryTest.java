package org.reelskill.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reelskill.data.mapper.CardMapper;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.reelskill.models.Role;
import org.reelskill.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DeckJdbcTemplateRepositoryTest {
    @Autowired
    JdbcTemplate  jdbcTemplate;

    @Autowired
    DeckJdbcTemplateRepository repository;

    static boolean hasRun = false;

    private static User USER2 = new User(2, "user2@example.com", "user2", "password2", Role.USER);

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void findAll() {
        List<Deck> decks = repository.findAll();

        assertTrue(decks.size() >= 3);
        assertTrue(decks.stream()
                .anyMatch(d -> d.getDeckId() == 1 && d.getDeckName().equals("Two Pointer")));
    }

    @Test
    void findById() {
        Deck deck = repository.findById(3);

        assertEquals(3,  deck.getDeckId());
        assertEquals(2, deck.getUserId());
        assertEquals("Depth-First Search", deck.getDeckName());
    }

    @Test
    void shouldAddDeck() {
        Deck deck = new Deck(
                0,
                2,
                "New Deck",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );

        Deck expected = new Deck(
                4,
                2,
                "New Deck",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );

        Deck actual = repository.add(deck);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateDeck() {
        Deck deck = repository.findById(2);

        assertEquals("Binary Search", deck.getDeckName());
        deck.setDeckName("New Name");
        assertTrue(repository.update(deck));

        Deck updatedDeck = repository.findById(2);
        assertEquals("New Name", updatedDeck.getDeckName());
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertNull(repository.findById(1));
    }

    // Helper method to retrieve cards for a deck
    private List<Card> getCardsForDeck(int deckId) {
        String sql = """
                select card_id, deck_id, front, back, created_at, updated_at
                from cards
                where deck_id = ?;
                """;
        return jdbcTemplate.query(sql, new CardMapper(), deckId);
    }
}

