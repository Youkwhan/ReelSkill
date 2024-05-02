package org.reelskill.data;

import org.reelskill.models.Deck;

import java.util.List;

public interface DeckRepository {
    List<Deck> findAll();

    Deck findByUserId(int userId);
}
