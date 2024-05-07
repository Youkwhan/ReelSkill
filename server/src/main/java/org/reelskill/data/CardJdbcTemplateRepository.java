package org.reelskill.data;

import org.reelskill.data.mapper.CardMapper;
import org.reelskill.models.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CardJdbcTemplateRepository implements CardRepository {
    private final JdbcTemplate jdbcTemplate;

    public CardJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Card> findAll() {
        String sql = """
                select
                card_id, deck_id, card_title, card_notes,
                leetcode_problem, number_of_times_reviewed, last_reviewed,
                created_at, updated_at, card_type_id, card_tag_id
                from cards;
                """;
        return jdbcTemplate.query(sql, new CardMapper());
    }

    @Override
    public Card findById(int cardId) {
        String sql = """
                select
                card_id, deck_id, card_title, card_notes,
                leetcode_problem, number_of_times_reviewed, last_reviewed,
                created_at, updated_at, card_type_id, card_tag_id
                from cards
                where card_id = ?;
                """;
        return jdbcTemplate.query(sql, new CardMapper(), cardId).stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    @Override
    public Card add(Card card) {
        String sql = """
                insert into cards
                (deck_id, card_title, card_notes, leetcode_problem,
                number_of_times_reviewed, card_type_id, card_tag_id)
                values (?, ?, ?, ?, ?, ?, ?)
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowAffected = jdbcTemplate.update((connection) -> {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, card.getDeckId());
            statement.setString(2, card.getCardTitle());
            statement.setString(3, card.getCardNotes());
            statement.setString(4, card.getLeetcodeProblem());
            statement.setInt(5, card.getNumberOfTimesReviewed());
            statement.setInt(6, card.getCardTypeId());
            statement.setInt(7, card.getCardTagId());
            return statement;
        }, keyHolder);

        if (rowAffected > 0) {
            card.setCardId(keyHolder.getKey().intValue());
            return card;
        }

        return null;
    }

    @Transactional
    @Override
    public boolean update(Card card) {
        String sql = """
                update cards set
                    deck_id = ?, card_title = ?, card_notes = ?, leetcode_problem = ?,
                    number_of_times_reviewed = ?, card_type_id = ?
                where card_id = ?;
                """;

        int rowsAffected = jdbcTemplate.update(sql,
                card.getDeckId(),
                card.getCardTitle(),
                card.getCardNotes(),
                card.getLeetcodeProblem(),
                card.getNumberOfTimesReviewed(),
                card.getCardTypeId(),
                card.getCardId());

        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteById(int cardId) {
        return jdbcTemplate.update("delete from cards where card_id = ?;", cardId) > 0;
    }

}
