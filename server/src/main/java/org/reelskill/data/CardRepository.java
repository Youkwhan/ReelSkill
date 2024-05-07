package org.reelskill.data;

import org.reelskill.models.Card;

import java.util.List;

public interface CardRepository {
    List<Card> findAll();
    Card findById(int cardId);
    Card add (Card card);
    boolean update(Card card);
    boolean deleteById(int cardId);
}
