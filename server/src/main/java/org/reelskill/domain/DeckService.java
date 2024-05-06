package org.reelskill.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.reelskill.data.DeckRepository;
import org.reelskill.models.Deck;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeckService {
    private final DeckRepository repository;
    private final Validator validator;

    public DeckService(DeckRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Deck> findAll() {
        return repository.findAll();
    }

    public Deck findById(int deckId) {
        return repository.findById(deckId);
    }

    public Result<Deck> save(Deck deck) {
        Result<Deck> result = validate(deck);
        if (!result.isSuccess()) {
            return result;
        }

        if (deck.getDeckId() > 0) { // update
            boolean success = repository.update(deck);
            if (!success) {
                String msg = String.format("deckId %s not found", deck.getDeckId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        } else { // add
            Deck withId = repository.add(deck);
            result.setPayload(withId);
        }

        return result;
    }

    public boolean deleteById(int deckId) {
        return repository.deleteById(deckId);
    }

    private Result<Deck> validate(Deck deck) {
        Result<Deck> result = new Result<>();
        if (deck == null) {
            result.addMessage("deck cannot be null", ResultType.INVALID);
            return result;
        }

        Set<ConstraintViolation<Deck>> violations = validator.validate(deck);
        for (var violation : violations) {
            result.addMessage(violation.getMessage(), ResultType.INVALID);
        }

        return result;
    }
}
