package org.reelskill.data.mapper;

import org.reelskill.models.CardTag;
import org.reelskill.models.Level;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardTagMapper implements RowMapper<CardTag>{
    @Override
    public CardTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        CardTag cardTag = new CardTag();
        cardTag.setCardTagId(rs.getInt("card_tag_id"));
        cardTag.setDifficultyLevel(Level.valueOf(rs.getString("difficulty_level").toUpperCase()));
        return cardTag;
    }
}
