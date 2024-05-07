package org.reelskill.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.reelskill.data.CardRepository;
import org.reelskill.models.Card;
import org.reelskill.models.Deck;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CardService {
    private final CardRepository repository;

    private final Validator validator;

    public CardService(CardRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Card> findAll() {
        return repository.findAll();
    }

    public Card findById(int cardId) {
        return repository.findById(cardId);
    }

    public Result<Card> save(Card card) {
        Result<Card> result = validate(card);
        if (!result.isSuccess()) {
            return result;
        }

        if (card.getCardId() > 0) {
            boolean success = repository.update(card);
            if (!success) {
                String msg = String.format("cardId %s not found", card.getCardId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        } else {
            Card withId = repository.add(card);
            result.setPayload(withId);
        }
        return result;
    }

    public Result<Card> updateCardType(Card card) {
        Result<Card> result = validate(card);
        if (!result.isSuccess()) {
            return result;
        }

        boolean success = repository.updateCardType(card);
        if (!success) {
            String msg = String.format("cardId %s not found", card.getCardId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int cardId) {
        return repository.deleteById(cardId);
    }

    private Result<Card> validate(Card card) {
        Result<Card> result = new Result<>();
        if (card == null) {
            result.addMessage("card cannot be null", ResultType.INVALID);
            return result;
        }

        Set<ConstraintViolation<Card>> violations = validator.validate(card);
        for (var violation : violations) {
            result.addMessage(violation.getMessage(), ResultType.INVALID);
        }

        return result;
    }
}
