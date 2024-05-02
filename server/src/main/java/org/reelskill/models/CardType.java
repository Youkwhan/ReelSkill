package org.reelskill.models;

public class CardType {
    private int cardTypeId;
    private Level difficultyLevel;

    public CardType() {}

    public CardType(int cardTypeId, Level difficultyLevel) {
        this.cardTypeId = cardTypeId;
        this.difficultyLevel = difficultyLevel;
    }
}
