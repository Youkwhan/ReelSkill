import { Accordion, AccordionBody, AccordionHeader } from 'react-bootstrap';
import './card.css';
import { useState } from 'react';
import {
  deleteById,
  update,
  findById,
  updateCardType,
} from '../../api/cardApi';

// function to determin className for cardTypeId
function getClassForCardType(cardTypeId) {
  switch (cardTypeId) {
    case null:
      return 'border-nothing';
    case 1:
      return 'border-green';
    case 2:
      return 'border-yellow';
    case 3:
      return 'border-red';
    default:
      return '';
  }
}

// Card Tag labels
function getCardTagLabel(cardTagId) {
  switch (cardTagId) {
    case null:
      return '';
    case 1:
      return 'easy';
    case 2:
      return 'med';
    case 3:
      return 'hard';
    default:
      return '';
  }
}

function index({ card, index, setDeck }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newCardTitle, setNewCardTitle] = useState(card.cardTitle ?? '');
  const [newCardNotes, setNewCardNotes] = useState(card.cardNotes ?? '');
  const [newLeetcodeProblem, setNewLeetcodeProblem] = useState(
    card.leetcodeProblem ?? ''
  );
  const [newCardTagId, setNewCardTagId] = useState(
    String(card.cardTagId ?? '')
  );

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleCancelEdit = () => {
    setIsEditing(false);
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();

    // update card
    const updatedCard = {
      cardId: card.cardId,
      deckId: card.deckId,
      cardTitle: newCardTitle,
      cardNotes: newCardNotes,
      leetcodeProblem: newLeetcodeProblem,
      cardTagId: parseInt(newCardTagId),
    };

    // update card
    update(updatedCard)
      .then(() => {
        console.log('Card updated successfully');
        findById(card.cardId).then((data) => {
          setDeck((prevDeck) => {
            const updatedDeck = { ...prevDeck };
            updatedDeck.cardList = updatedDeck.cardList.map((c) =>
              c.cardId === data.cardId ? data : c
            );
            return updatedDeck;
          });
        });
        setIsEditing(false);
      })
      .catch((error) => {
        console.error('Error updating card', error);
      });
  };

  const handleDelete = () => {
    console.log(card);
    // delete deck
    deleteById(card.cardId).then(() => {
      console.log('Card deleted successfully');
      setDeck((prevDeck) => {
        const updatedDeck = { ...prevDeck };
        updatedDeck.cardList = updatedDeck.cardList.filter(
          (c) => c.cardId !== card.cardId
        );
        return updatedDeck;
      });
    });
  };

  const handleCardType = (card, cardTypeId) => {
    const updatedCard = {
      cardId: card.cardId,
      cardTypeId: parseInt(cardTypeId),
      numberOfTimesReviewed: card.numberOfTimesReviewed + 1,
    };

    // update cardTtype
    updateCardType(updatedCard)
      .then(() => {
        console.log('CardType updated successfully');
        findById(card.cardId).then((data) => {
          setDeck((prevDeck) => {
            const updatedDeck = { ...prevDeck };
            updatedDeck.cardList = updatedDeck.cardList.map((c) =>
              c.cardId === data.cardId ? data : c
            );
            return updatedDeck;
          });
        });
        setIsEditing(false);
      })
      .catch((error) => {
        console.error('Error updating cardType', error);
      });
  };

  return (
    <Accordion.Item eventKey={index}>
      <AccordionHeader
        className={`card-tab ${getClassForCardType(card.cardTypeId)}`}
      >
        <div className="card-header">
          <div className="card-header-left">
            <span>{card.cardTitle}</span>
            {card.cardTagId && (
              <div className={`card-label ${getCardTagLabel(card.cardTagId)}`}>
                {getCardTagLabel(card.cardTagId)}
              </div>
            )}
          </div>

          <button
            className="btn btn-outline-danger delete-btn  btn-sm"
            onClick={handleDelete}
          >
            <i className="bi bi-trash"></i>
          </button>
        </div>
      </AccordionHeader>
      <AccordionBody>
        {isEditing ? (
          <form onSubmit={handleFormSubmit}>
            <div className="mb-3">
              <label htmlFor="cardTitle" className="form-label">
                Card Title
              </label>
              <input
                type="text"
                className="form-control"
                id="cardTitle"
                value={newCardTitle}
                onChange={(e) => setNewCardTitle(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="cardNotes" className="form-label">
                Card Notes
              </label>
              <textarea
                className="form-control"
                id="cardNotes"
                rows="3"
                value={newCardNotes}
                onChange={(e) => setNewCardNotes(e.target.value)}
                required
              ></textarea>
            </div>
            <div className="mb-3">
              <label htmlFor="leetcodeProblem" className="form-label">
                Leetcode Problem
              </label>
              <input
                type="text"
                className="form-control"
                id="leetcodeProblem"
                value={newLeetcodeProblem}
                onChange={(e) => setNewLeetcodeProblem(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Card Tag</label>
              <div>
                <input
                  type="radio"
                  id="easy"
                  name="cardTag"
                  value="1"
                  checked={newCardTagId === '1'}
                  onChange={(e) => setNewCardTagId(e.target.value)}
                />
                <label htmlFor="easy" className="ml-2 mr-3">
                  Easy
                </label>
                <input
                  type="radio"
                  id="med"
                  name="cardTag"
                  value="2"
                  checked={newCardTagId === '2'}
                  onChange={(e) => setNewCardTagId(e.target.value)}
                />
                <label htmlFor="med" className="ml-2 mr-3">
                  Medium
                </label>
                <input
                  type="radio"
                  id="hard"
                  name="cardTag"
                  value="3"
                  checked={newCardTagId === '3'}
                  onChange={(e) => setNewCardTagId(e.target.value)}
                />
                <label htmlFor="hard" className="ml-2">
                  Hard
                </label>
              </div>
            </div>
            <div className="button-actions">
              <button
                type="submit"
                className="btn btn-outline-success check-btn btn-sm"
              >
                Save <i className="bi bi-check"></i>
              </button>
              <button
                className="btn btn-outline-secondary cancel-edit-btn btn-sm"
                onClick={handleCancelEdit}
              >
                Cancel <i className="bi bi-x"></i>
              </button>
            </div>
          </form>
        ) : (
          <>
            <div>
              <p>
                <strong>Problem Statement: </strong>
              </p>
              <p>
                <strong>Leetcode: </strong>
                <a
                  href={card.leetcodeProblem}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  {card.leetcodeProblem}
                </a>
              </p>
              <p>
                <strong>Pseudocode Notes: </strong>
                <br />
                {card.cardNotes}
              </p>
              <div className="card-cardtype-btns">
                <button
                  className="easy"
                  onClick={() => handleCardType(card, '1')}
                >
                  easy
                </button>
                {' / '}
                <button
                  className="med"
                  onClick={() => handleCardType(card, '2')}
                >
                  med
                </button>
                {' / '}
                <button
                  className="hard"
                  onClick={() => handleCardType(card, '3')}
                >
                  hard
                </button>
              </div>
            </div>
            <div className="button-actions">
              <button
                className="btn btn-outline-primary edit-btn btn-sm"
                onClick={handleEdit}
              >
                <i className="bi bi-pencil"></i>
              </button>
            </div>
          </>
        )}
      </AccordionBody>
    </Accordion.Item>
  );
}

export default index;
