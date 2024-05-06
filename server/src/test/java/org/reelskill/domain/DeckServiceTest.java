package org.reelskill.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.reelskill.data.DeckRepository;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DeckServiceTest {
    @Autowired
    DeckService service;

    @MockBean
    DeckRepository repository;

    @Bean
    static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldNotSaveWithoutValidName() {
        Deck deck = new Deck();
        deck.setDeckName("Deck Name");
        deck.setDeckId(4);

        Result<Deck> actual = service.save(deck);

        assertFalse(actual.isSuccess());
    }

    @Test
    void  shouldSaveUpdate() {
        when(repository.update(any())).thenReturn(true);

        Deck deck = new Deck();
        deck.setDeckName("Deck Name");
        deck.setDeckId(4);
        deck.setUserId(1);

        Result<Deck> actual = service.save(deck);
        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldDeleteDeckById() {
        when(repository.deleteById(1)).thenReturn(true);
        assertTrue(service.deleteById(1));
    }

    @Test
    void shouldNotDeleteDeckById() {
        assertFalse(service.deleteById(10));
    }

    @Test
    void shouldAdd() {
        Deck deck = new Deck();
        deck.setDeckName("Deck Name");
        deck.setDeckId(0);
        deck.setUserId(1);

        when(repository.add(deck)).thenReturn(deck);
        Result<Deck> actual = service.save(deck);
        assertTrue(actual.isSuccess());
    }

}