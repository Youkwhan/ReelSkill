import React, { useState } from 'react';
import { useAuthContext } from '../../hooks';
import Card from '../../components/Card';

function ReviewHeader({ deck }) {
  const [editing, setEditing] = useState(false);
  const [newCardTitle, setNewCardTitle] = useState('');
  // error
  const [showError, setShowError] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleEditClick = () => {
    setEditing(true);
    setShowError(false);
    setErrorMessage('');
  };

  const handleInputChange = (e) => {
    setNewCardTitle(e.target.value);
  };

  const handleCreateClick = () => {};

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
          <div className="editing-controls">
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
        {deck &&
          deck.cardList.map((card) => <Card key={card.cardId} card={card} />)}
      </div>
    </div>
  );
}

export default ReviewHeader;
