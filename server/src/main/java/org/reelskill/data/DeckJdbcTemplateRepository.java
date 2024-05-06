package org.reelskill.data;

import org.reelskill.data.mapper.CardMapper;
import org.reelskill.data.mapper.DeckMapper;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
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
    public Deck findById(int deckId) {
        String sql = """
                select deck_id, user_id, deck_name, created_at, updated_at
                from decks
                where deck_id = ?;
                """;
        Deck deck =  jdbcTemplate.query(sql, new DeckMapper(), deckId).stream()
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
                values (?, ?);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = jdbcTemplate.update((connection) -> {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, deck.getUserId());
            statement.setString(2, deck.getDeckName());
            return statement;
        }, keyHolder);

        if (rowAffected > 0) {
            deck.setDeckId(keyHolder.getKey().intValue());
            // but new deck has no cards?
            updateCardsToDeck(deck);
            return deck;
        }

        return null;
    }

    @Transactional
    @Override
    public boolean update(Deck deck) {
        String sql = """
                update decks set
                    user_id = ?,
                    deck_name = ?
                where deck_id = ?;
                """;

        int rowsAffected  = jdbcTemplate.update(sql,
                deck.getUserId(),
                deck.getDeckName(),
                deck.getDeckId());

        if (rowsAffected > 0) {
//            updateCardsToDeck(deck);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteById(int deckId) {
        jdbcTemplate.update("delete from cards where deck_id = ?;", deckId);
        return jdbcTemplate.update("delete from decks where deck_id = ?;", deckId) > 0;
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

    private void updateCardsToDeck(Deck deck) {
        jdbcTemplate.update("delete from cards where deck_id = ?;", deck.getDeckId());
        for (Card c : deck.getCardList()) {
            String sql = """
                    insert into cards (deck_id, card_title, card_notes, leetcode_problem,
                        number_of_times_reviewed, last_reviewed, card_type_id, card_tag_id)
                    values (?, ?, ?, ?, ?, ?, ?, ?);
                    """;
            jdbcTemplate.update(sql,
                    deck.getDeckId(),
                    c.getCardTitle(),
                    c.getCardNotes(),
                    c.getLeetcodeProblem(),
                    c.getNumberOfTimesReviewed(),
                    c.getLastReviewed(),
                    c.getCardTypeId(),
                    c.getCardTagId());
        }
    }
}
