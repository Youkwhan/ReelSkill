package org.reelskill.data.mapper;

import org.reelskill.models.Deck;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeckMapper implements RowMapper<Deck> {
    @Override
    public Deck mapRow(ResultSet rs, int rowNum) throws SQLException {
        Deck deck = new Deck();
        deck.setDeckId(rs.getInt("deck_id"));
        deck.setUserId(rs.getInt("user_id"));
        deck.setDeckName(rs.getString("deck_name"));
        deck.setCreatedAt(rs.getTimestamp("created_at"));
        deck.setUpdatedAt(rs.getTimestamp("updated_at"));
        return deck;
    }
}
