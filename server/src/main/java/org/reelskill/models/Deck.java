package org.reelskill.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Deck {
    private int deckId;
    @NotNull(message = "User ID must not be null")
    private int userId;
    @NotBlank(message = "Deck name must not be blank")
    @Size(max = 100, message = "Deck name must be less than 100 characters")
    private String deckName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Card> cardList;

    public Deck() {}
    public Deck(int deckId, int userId, String deckName, Timestamp createdAt, Timestamp updatedAt, List<Card> cardList) {
        this.deckId = deckId;
        this.userId = userId;
        this.deckName = deckName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cardList = cardList;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
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

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return deckId == deck.deckId && userId == deck.userId && Objects.equals(deckName, deck.deckName) && Objects.equals(createdAt, deck.createdAt) && Objects.equals(updatedAt, deck.updatedAt) && Objects.equals(cardList, deck.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckId, userId, deckName, createdAt, updatedAt, cardList);
    }
}
