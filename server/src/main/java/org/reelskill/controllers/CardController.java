package org.reelskill.controllers;

import jakarta.validation.Valid;
import org.reelskill.domain.CardService;
import org.reelskill.domain.Result;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.reelskill.controllers.ResponseEntityExtensions.makeBindingResultResponseEntity;
import static org.reelskill.controllers.ResponseEntityExtensions.makeResponseEntity;

@RestController
@RequestMapping("/api/card")
public class CardController {
    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public List<Card> getCards() {
        return service.findAll();
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCard(@PathVariable int cardId) {
        Card card = service.findById(cardId);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Card card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return makeBindingResultResponseEntity(bindingResult);
        }

        Result<Card> result = service.save(card);

        if (result.isSuccess()) {
            return ResponseEntity.created(
                    URI.create("http://localhost:8080/api/card/" + result.getPayload().getCardId())).body(card);
        }
        return makeResponseEntity(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<?> put(@PathVariable int cardId, @Valid @RequestBody Card card, BindingResult bindingResult) {
        if (cardId != card.getCardId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (bindingResult.hasErrors()) {
            return makeBindingResultResponseEntity(bindingResult);
        }

        Result<Card> result = service.save(card);
        return makeResponseEntity(result, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> delete(@PathVariable int cardId) {
        boolean success = service.deleteById(cardId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
