import React, { useState } from 'react';
import Card from '../../components/Card';
import { Accordion } from 'react-bootstrap';
import { add } from '../../api/cardApi';
import { findById } from '../../api/deckApi';

function ReviewHeader({ deck, setDeck }) {
  const [editing, setEditing] = useState(false);
  const [newCardTitle, setNewCardTitle] = useState('');
  // error
  const [showError, setShowError] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  // sorting by card id initally
  const sortedCards =
    deck &&
    [...deck.cardList].sort((a, b) => {
      // handle null, 3, 2, 1
      if (a.cardTypeId === null && b.cardTypeId !== null) {
        return -1;
      }
      if (a.cardTypeId !== null && b.cardTypeId === null) {
        return 1;
      }
      // sorting in descending order
      if (a.cardTypeId !== null && b.cardTypeId !== null) {
        return b.cardTypeId - a.cardTypeId;
      }
      return 0;
    });

  const handleEditClick = () => {
    setEditing(true);
    setShowError(false);
    setErrorMessage('');
  };

  const handleInputChange = (e) => {
    setNewCardTitle(e.target.value);
  };

  const handleCreateClick = () => {
    const trimmedTitle = newCardTitle.trim();

    if (!trimmedTitle) {
      setErrorMessage('Card title cannot be empty');
      setShowError(true);
      return;
    }

    const newCard = {
      deckId: deck.deckId,
      cardTitle: trimmedTitle,
    };

    // add function
    add(newCard)
      .then(() => {
        console.log('Card saved successfully');
        // reload deck
        findById(deck.deckId).then((data) => {
          setDeck(data);
        });
        setShowError(false);
      })
      .catch((error) => {
        console.error('Error saving card', error);
        setShowError(true);
        setErrorMessage('Error saving card');
      })
      .finally(() => {
        setEditing(false);
        setNewCardTitle('');
      });
  };

  const handleDeleteClick = () => {
    setEditing(false);
    setNewCardTitle('');
  };

  return (
    <div className="reviewheader-container">
      <div className="reviewheader-header">
        <p>{deck && deck.deckName}</p>
        {!editing && (
          <button className="btn-add-deck" onClick={handleEditClick}>
            <i className="bi bi-plus-square"></i>
          </button>
        )}
      </div>
      {editing && (
        <>
          <div className="editing-controls mb-2">
            <input
              type="text"
              className="form-control"
              value={newCardTitle}
              onChange={handleInputChange}
            />
            <div className="editing-btns">
              <button
                className="btn btn-outline-success save-btn btn-sm"
                onClick={handleCreateClick}
              >
                <i className="bi bi-plus"></i>
              </button>
              <button
                className="btn btn-outline-secondary cancel-btn btn-sm"
                onClick={handleDeleteClick}
              >
                <i className="bi bi-x"></i>
              </button>
            </div>
          </div>
          {showError && (
            <p className="alert alert-danger error-message" role="alert">
              {errorMessage}
            </p>
          )}
        </>
      )}
      <div>
        {/* render Cards */}
        <Accordion>
          {sortedCards &&
            sortedCards.map((card, index) => (
              <Card
                key={card.cardId}
                card={card}
                index={index}
                setDeck={setDeck}
              />
            ))}
        </Accordion>
      </div>
    </div>
  );
}

export default ReviewHeader;
