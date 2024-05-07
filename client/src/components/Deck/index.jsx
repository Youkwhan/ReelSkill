import { useState } from 'react';
import './style.css';
import { deleteById } from '../../api/deckApi';
import { findById } from '../../api/userApi';
import { useAuthContext } from '../../hooks';
import { save } from '../../api/deckApi';

function Deck({ deck }) {
  const { user, login } = useAuthContext();
  const [editing, setEditing] = useState(false);
  const [newDeckName, setNewDeckName] = useState(deck.deckName);
  // error
  const [showError, setShowError] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleEdit = () => {
    setEditing(true);
    setShowError(false);
    setErrorMessage('');
  };

  const handleSave = () => {
    // update DECK
    const trimmedTitle = newDeckName.trim();

    if (!trimmedTitle) {
      setErrorMessage('Deck title cannot be empty');
      setShowError(true);
      return;
    }

    // updated deck
    const updatedDeck = {
      deckId: deck.deckId,
      userId: user.userId,
      deckName: trimmedTitle,
    };

    // update function
    save(updatedDeck)
      .then(() => {
        // success
        console.log('Deck updated successfully');
        findById(user.userId).then((data) => {
          login(data);
        });
        setShowError(false);
      })
      .catch((error) => {
        // error
        console.error('Error updating deck', error);
        setShowError(true);
        setErrorMessage('Error updating deck');
      })
      .finally(() => {
        setEditing(false);
      });
  };

  const handleCancel = () => {
    setNewDeckName(deck.deckName);
    setEditing(false);
  };

  const handleDelete = () => {
    // delete DECK
    deleteById(deck.deckId).then(() => {
      console.log('Deck deleted successfully');
      findById(user.userId).then((data) => {
        login(data);
      });
    });
  };

  const handleInputChange = (event) => {
    setNewDeckName(event.target.value);
  };

  return (
    <div className="deck-tab">
      <div className="deck-content">
        {editing ? (
          <>
            <div>
              <input
                type="text"
                className="form-control"
                value={newDeckName}
                onChange={handleInputChange}
              />
              <button
                className="btn btn-outline-success check-btn btn-sm"
                onClick={handleSave}
              >
                <i className="bi bi-check"></i>
              </button>
              <button
                className="btn btn-outline-secondary cancel-btn btn-sm"
                onClick={handleCancel}
              >
                <i className="bi bi-x"></i>
              </button>
            </div>
            <div>
              {showError && (
                <p className="alert alert-danger error-message" role="alert">
                  {errorMessage}
                </p>
              )}
            </div>
          </>
        ) : (
          <div>
            <span>{deck.deckName}</span>
            <div className="deck-actions">
              <button
                className="btn btn-outline-primary edit-btn btn-sm"
                onClick={handleEdit}
              >
                <i className="bi bi-pencil"></i>
              </button>
              <button
                className="btn btn-outline-danger delete-btn  btn-sm"
                onClick={handleDelete}
              >
                <i className="bi bi-trash"></i>
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default Deck;
