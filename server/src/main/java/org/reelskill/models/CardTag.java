package org.reelskill.models;

public class CardTag {
    private int cardTagId;
    private Level difficultyLevel;

    public CardTag() {}

    public CardTag(int cardTagId, Level difficultyLevel) {
        this.cardTagId = cardTagId;
        this.difficultyLevel = difficultyLevel;
    }
}
