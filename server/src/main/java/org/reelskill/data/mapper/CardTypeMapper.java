package org.reelskill.data.mapper;

import org.reelskill.models.CardType;
import org.reelskill.models.Level;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardTypeMapper implements RowMapper<CardType>{
    @Override
    public CardType mapRow(ResultSet rs, int rowNum) throws SQLException {
        CardType cardType = new CardType();
        cardType.setCardTypeId(rs.getInt("card_type_id"));
        cardType.setDifficultyLevel(Level.valueOf(rs.getString("difficulty_level").toUpperCase()));
        return cardType;
    }
}
