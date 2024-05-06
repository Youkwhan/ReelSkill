package org.reelskill.models;

public class CardType {
    private int cardTypeId;
    private Level difficultyLevel;

    public CardType() {}

    public CardType(int cardTypeId, Level difficultyLevel) {
        this.cardTypeId = cardTypeId;
        this.difficultyLevel = difficultyLevel;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Level getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Level difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
