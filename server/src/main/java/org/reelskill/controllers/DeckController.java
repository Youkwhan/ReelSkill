package org.reelskill.controllers;

import jakarta.validation.Valid;
import org.reelskill.domain.DeckService;
import org.reelskill.domain.Result;
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
@RequestMapping("/api/deck")
public class DeckController {
    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    @GetMapping
    public List<Deck> getDecks() {
        return service.findAll();
    }

    @GetMapping("/{deckId}")
    public ResponseEntity<Deck> getDeck(@PathVariable int deckId) {
        Deck deck  = service.findById(deckId);
        if (deck == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deck);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Deck deck, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return makeBindingResultResponseEntity(bindingResult);
        }

        Result<Deck> result = service.save(deck);

        if (result.isSuccess()) {
            return ResponseEntity.created(
                    URI.create("http://localhost:8080/api/deck/" + result.getPayload().getDeckId()))
                            .body(deck);
        }
        return makeResponseEntity(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{deckId}")
    public ResponseEntity<?>  put(@PathVariable  int deckId, @Valid @RequestBody Deck deck, BindingResult bindingResult) {
        if (deckId != deck.getDeckId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (bindingResult.hasErrors()) {
            return makeBindingResultResponseEntity(bindingResult);
        }

        Result<Deck> result = service.save(deck);
        return makeResponseEntity(result, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{deckId}")
    public ResponseEntity<Void> delete(@PathVariable int deckId) {
        boolean success = service.deleteById(deckId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
