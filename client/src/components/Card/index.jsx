import { Accordion, AccordionBody, AccordionHeader } from 'react-bootstrap';
import './card.css';
import { useState } from 'react';

function index({ card, index }) {
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

  const handleSave = () => {};

  const handleDelete = () => {};

  return (
    <Accordion.Item eventKey={index}>
      <AccordionHeader className="card-tab">
        <div className="card-header">
          <span>{card.cardTitle}</span>

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
          <form onSubmit={handleSave}>
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
              <div>
                <button>easy</button> {' / '}
                <button>med</button> {' / '}
                <button>hard</button>
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
