package org.reelskill.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reelskill.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CardJdbcTemplateRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CardJdbcTemplateRepository repository;
    static boolean hasRun = false;

    @BeforeEach
    void setup() {
        if (!hasRun) {
            jdbcTemplate.update("call set_known_good_state();");
            hasRun = true;
        }
    }

    @Test
    void findAll() {
        List<Card> cards = repository.findAll();

        assertTrue(cards.size() >= 5);
        assertTrue(cards.stream()
                .anyMatch(c -> c.getCardId() == 1 && c.getCardTitle().equals("1. Two Sum")));
    }

    @Test
    void findById() {
        Card card = repository.findById(1);

        assertEquals(1, card.getCardId());
        assertEquals(1, card.getDeckId());
        assertEquals("1. Two Sum", card.getCardTitle());
        assertEquals(1, card.getCardTagId());
    }

    @Test
    void shouldAddCard() {
        Card card = new Card(
                0,
                1,
                "New Card",
                "New Card Notes",
                "New Leetcode Problem",
                0,
                null,
                null,
                null,
                1,
                1
        );

        Card expected = new Card (
                6,
                1,
                "New Card",
                "New Card Notes",
                "New Leetcode Problem",
                0,
                null,
                null,
                null,
                1,
                1
        );

        Card actual = repository.add(card);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateCard() {
        Card card = repository.findById(1);

        assertEquals("1. Two Sum", card.getCardTitle());
        card.setCardTitle("New Title");
        assertTrue(repository.update(card));

        Card updatedCard = repository.findById(1);
        assertEquals("New Title", updatedCard.getCardTitle());
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertNull(repository.findById(1));
    }
}