package org.reelskill.models;

import java.sql.Timestamp;

public class Card {
    private int cardId;
    private int deckId;
    private String cardTitle;
    private String cardNotes;
    private String leetcodeProblem;
    private int numberOfTimesReviewed;
    private Timestamp lastReviewed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int cardTypeId;
    private int cardTagId;

    public Card() {}

    public Card(int cardId, int deckId, String cardTitle, String cardNotes, String leetcodeProblem, int numberOfTimesReviewed, Timestamp lastReviewed, Timestamp createdAt, Timestamp updatedAt, int cardTypeId, int cardTagId) {
        this.cardId = cardId;
        this.deckId = deckId;
        this.cardTitle = cardTitle;
        this.cardNotes = cardNotes;
        this.leetcodeProblem = leetcodeProblem;
        this.numberOfTimesReviewed = numberOfTimesReviewed;
        this.lastReviewed = lastReviewed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cardTypeId = cardTypeId;
        this.cardTagId = cardTagId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardNotes() {
        return cardNotes;
    }

    public void setCardNotes(String cardNotes) {
        this.cardNotes = cardNotes;
    }

    public String getLeetcodeProblem() {
        return leetcodeProblem;
    }

    public void setLeetcodeProblem(String leetcodeProblem) {
        this.leetcodeProblem = leetcodeProblem;
    }

    public int getNumberOfTimesReviewed() {
        return numberOfTimesReviewed;
    }

    public void setNumberOfTimesReviewed(int numberOfTimesReviewed) {
        this.numberOfTimesReviewed = numberOfTimesReviewed;
    }

    public Timestamp getLastReviewed() {
        return lastReviewed;
    }

    public void setLastReviewed(Timestamp lastReviewed) {
        this.lastReviewed = lastReviewed;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public int getCardTagId() {
        return cardTagId;
    }

    public void setCardTagId(int cardTagId) {
        this.cardTagId = cardTagId;
    }
}
