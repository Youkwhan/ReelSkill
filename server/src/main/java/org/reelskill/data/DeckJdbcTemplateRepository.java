package org.reelskill.data;

import org.reelskill.data.mapper.CardMapper;
import org.reelskill.data.mapper.DeckMapper;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeckJdbcTemplateRepository implements DeckRepository{
    private final JdbcTemplate jdbcTemplate;

    public DeckJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Deck> findAll() {
        String sql = """
                select deck_id, user_id, deck_name, created_at, updated_at
                from decks;
                """;
        List<Deck> decks = jdbcTemplate.query(sql, new DeckMapper());
        for (Deck d : decks) {
            assignCardsToDeck(d);
        }
        return decks;
    }

    @Override
    public Deck findByUserId(int userId) {
        String sql = """
                select deck_id, user_id, deck_name, created_at, updated_at
                from decks
                where user_id = ?;
                """;
        Deck deck =  jdbcTemplate.query(sql, new DeckMapper(), userId).stream()
                .findFirst()
                .orElse(null);
        if (deck != null) {
            assignCardsToDeck(deck);
        }
        return deck;
    }

    @Transactional
    @Override
    public Deck add(Deck deck) {
        String sql = """
                insert into decks (user_id, deck_name)
                values (?, ?)
                returning deck_id;
                """;
        return null;
    }

    @Override
    public boolean update(Deck deck) {
        return false;
    }

    @Override
    public boolean deleteById(int deckId) {
        return false;
    }


    private void assignCardsToDeck(Deck deck) {
        String sql = """
                select card_id, deck_id, card_title, card_notes, leetcode_problem,
                    number_of_times_reviewed, last_reviewed, created_at, updated_at,
                    card_type_id, card_tag_id
                from cards
                where deck_id = ?;
                """;
        List<Card> cards = jdbcTemplate.query(sql, new CardMapper(), deck.getDeckId());
        deck.setCardList(cards);
    }

}
