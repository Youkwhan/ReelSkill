package org.reelskill.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.reelskill.data.CardRepository;
import org.reelskill.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CardServiceTest {
    @Autowired
    CardService service;

    @MockBean
    CardRepository repository;

    @Bean
    static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldNotSaveWithoutValidName() {
        Card card = new Card();
        card.setCardTitle("Card Name");
        card.setCardId(5);

        Result<Card> actual = service.save(card);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldSaveUpdate() {
        when(repository.update(any())).thenReturn(true);

        Card card = new Card();
        card.setCardId(5);
        card.setDeckId(3);
        card.setCardTitle("100. Card Name");

        Result<Card> actual = service.save(card);
        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldUpdateCardType() {
        when(repository.updateCardType(any())).thenReturn(true);

        Card card = new Card();
        card.setCardId(5);
        card.setDeckId(3);
        card.setCardTitle("100. Card Name");

        Result<Card> actual = service.updateCardType(card);
        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldDeleteCardById() {
        when(repository.deleteById(1)).thenReturn(true);
        assertTrue(service.deleteById(1));
    }

    @Test
    void shouldNotDeleteCardById() {
        assertFalse(service.deleteById(100));
    }

    @Test
    void shouldAdd() {
        Card card = new Card(
                0,
                1,
                "New Card",
                "New Card Notes",
                "New Leetcode Problem",
                0,
                null,
                null,
                null,
                null,
                1
        );

        when(repository.add(any())).thenReturn(card);

        Result<Card> actual = service.save(card);
        assertTrue(actual.isSuccess());
    }
}