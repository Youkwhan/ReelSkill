package org.reelskill.models;

public class CardTag {
    private int cardTagId;
    private Level difficultyLevel;

    public CardTag() {}

    public CardTag(int cardTagId, Level difficultyLevel) {
        this.cardTagId = cardTagId;
        this.difficultyLevel = difficultyLevel;
    }

    public int getCardTagId() {
        return cardTagId;
    }

    public void setCardTagId(int cardTagId) {
        this.cardTagId = cardTagId;
    }

    public Level getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Level difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
