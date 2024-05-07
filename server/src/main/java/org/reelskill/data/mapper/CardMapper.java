package org.reelskill.data.mapper;

import org.reelskill.models.Card;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();
        card.setCardId(rs.getInt("card_id"));
        card.setDeckId(rs.getInt("deck_id"));
        card.setCardTitle(rs.getString("card_title"));
        card.setCardNotes(rs.getString("card_notes"));
        card.setLeetcodeProblem(rs.getString("leetcode_problem"));
        card.setNumberOfTimesReviewed(rs.getInt("number_of_times_reviewed"));
        card.setLastReviewed(rs.getTimestamp("last_reviewed"));
        card.setCreatedAt(rs.getTimestamp("created_at"));
        card.setUpdatedAt(rs.getTimestamp("updated_at"));
        card.setCardTypeId(rs.getInt("card_type_id"));
        if (rs.wasNull()) {
            card.setCardTypeId(null);
        }
        card.setCardTagId(rs.getInt("card_tag_id"));
        return card;
    }
}
