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

    public int getDeck_id() {
        return deckId;
    }

    public void setDeck_id(int deckId) {
        this.deckId = deckId;
    }

    public String getCard_title() {
        return cardTitle;
    }

    public void setCard_title(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCard_notes() {
        return cardNotes;
    }

    public void setCard_notes(String cardNotes) {
        this.cardNotes = cardNotes;
    }

    public String getLeetcode_problem() {
        return leetcodeProblem;
    }

    public void setLeetcode_problem(String leetcodeProblem) {
        this.leetcodeProblem = leetcodeProblem;
    }

    public int getNumber_of_times_reviewed() {
        return numberOfTimesReviewed;
    }

    public void setNumber_of_times_reviewed(int numberOfTimesReviewed) {
        this.numberOfTimesReviewed = numberOfTimesReviewed;
    }

    public Timestamp getLast_reviewed() {
        return lastReviewed;
    }

    public void setLast_reviewed(Timestamp lastReviewed) {
        this.lastReviewed = lastReviewed;
    }

    public Timestamp getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdated_at() {
        return updatedAt;
    }

    public void setUpdated_at(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCardType_id() {
        return cardTypeId;
    }

    public void setCardType_id(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public int getCardTag_id() {
        return cardTagId;
    }

    public void setCardTag_id(int cardTagId) {
        this.cardTagId = cardTagId;
    }
}
