package org.reelskill.data;

import org.reelskill.data.mapper.CardMapper;
import org.reelskill.data.mapper.DeckMapper;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                select deck_id, user_id, deck_name, created_at
                from decks;
                """;
        List<Deck> decks = jdbcTemplate.query(sql, new DeckMapper());
        for (Deck d : decks) {
            assignCardsToDeck(d);
        }
        return decks;
    }

    private void assignCardsToDeck(Deck deck) {
        String sql = """
                select card_id, deck_id, card_name, card_type, card_text
                from cards
                where deck_id = ?;
                """;
        List<Card> cards = jdbcTemplate.query(sql, new CardMapper(), deck.getDeckId());
        deck.setCardList(cards);
    }

}
