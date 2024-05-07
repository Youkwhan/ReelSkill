import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';
import Deck from '../Deck';
import { useAuthContext } from '../../hooks';
import './style.css';

function Sidebar({ iconClass, ...props }) {
  const { user } = useAuthContext();
  // side bar toggle
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const toggleShow = () => setShow((s) => !s);

  // Deck edit toggle
  const [editing, setEditing] = useState(false);
  const [newDeckTitle, setNewDeckTitle] = useState('');
  const handleEditClick = () => {
    setEditing(true);
  };
  const handleInputChange = (e) => {
    setNewDeckTitle(e.target.value);
  };

  const handleCreateClick = () => {
    // create Deck
    setEditing(false);
    setNewDeckTitle('');
  };

  const handleDeleteClick = () => {
    setEditing(false);
    setNewDeckTitle('');
  };

  return (
    <>
      <Button variant="link-dark" onClick={toggleShow} className="me-2">
        <i className={iconClass}></i>
      </Button>
      <Offcanvas show={show} onHide={handleClose} {...props}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>ReelSkill</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body>
          <div>
            <div className="sidebar-header">
              <p>Deck</p>
              {!editing && (
                <button className="btn-add-deck" onClick={handleEditClick}>
                  <i className="bi bi-plus-square"></i>
                </button>
              )}
            </div>
            {editing && (
              <div className="editing-controls">
                <input
                  type="text"
                  className="form-control"
                  value={newDeckTitle}
                  onChange={handleInputChange}
                />
                <div className="editing-btns">
                  <button
                    className="btn btn-outline-success save-btn btn-sm"
                    onClick={handleCreateClick}
                  >
                    <i class="bi bi-plus"></i>
                  </button>
                  <button
                    className="btn btn-outline-secondary cancel-btn btn-sm"
                    onClick={handleDeleteClick}
                  >
                    <i className="bi bi-x"></i>
                  </button>
                </div>
              </div>
            )}
            <div>
              {user.deckList.map((deck) => (
                <Deck key={deck.deckId} deck={deck} />
              ))}
            </div>
          </div>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}

export default Sidebar;
