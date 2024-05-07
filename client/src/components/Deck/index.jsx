import { useState } from 'react';
import './style.css';

function Deck({ deck }) {
  console.log(deck);
  const [editing, setEditing] = useState(false);
  const [newDeckName, setNewDeckName] = useState(deck.deckName);

  const handleEdit = () => {
    setEditing(true);
  };
  const handleSave = () => {
    // update DECK
    setEditing(false);
  };
  const handleCancel = () => {
    setNewDeckName(deck.deckName);
    setEditing(false);
  };
  const handleDelete = () => {
    // delete DECK
  };
  const handleInputChange = (event) => {
    setNewDeckName(event.target.value);
  };

  return (
    <div className="deck-tab">
      <div className="deck-content">
        {editing ? (
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
              <i class="bi bi-check"></i>
            </button>
            <button
              className="btn btn-outline-secondary cancel-btn btn-sm"
              onClick={handleCancel}
            >
              <i className="bi bi-x"></i>
            </button>
          </div>
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
