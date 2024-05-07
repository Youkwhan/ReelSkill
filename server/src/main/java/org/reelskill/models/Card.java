package org.reelskill.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Objects;

public class Card {
    private int cardId;
    @NotNull(message = "Deck ID must not be null")
    private int deckId;
//    @NotNull(message = "Card title is required")
//    @NotBlank(message = "Card title is required")
//    @Size(max = 100, message = "Deck name must be less than 100 characters")
    private String cardTitle;
    private String cardNotes;
    private String leetcodeProblem;
    private int numberOfTimesReviewed;
    private Timestamp lastReviewed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer cardTypeId;
    private int cardTagId;

    public Card() {}

    public Card(int cardId, int deckId, String cardTitle, String cardNotes, String leetcodeProblem, int numberOfTimesReviewed, Timestamp lastReviewed, Timestamp createdAt, Timestamp updatedAt, Integer cardTypeId, int cardTagId) {
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

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public int getCardTagId() {
        return cardTagId;
    }

    public void setCardTagId(int cardTagId) {
        this.cardTagId = cardTagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId == card.cardId && deckId == card.deckId && numberOfTimesReviewed == card.numberOfTimesReviewed && cardTagId == card.cardTagId && Objects.equals(cardTitle, card.cardTitle) && Objects.equals(cardNotes, card.cardNotes) && Objects.equals(leetcodeProblem, card.leetcodeProblem) && Objects.equals(lastReviewed, card.lastReviewed) && Objects.equals(createdAt, card.createdAt) && Objects.equals(updatedAt, card.updatedAt) && Objects.equals(cardTypeId, card.cardTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, deckId, cardTitle, cardNotes, leetcodeProblem, numberOfTimesReviewed, lastReviewed, createdAt, updatedAt, cardTypeId, cardTagId);
    }
}
