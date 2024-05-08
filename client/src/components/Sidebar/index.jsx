import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';
import Deck from '../Deck';
import { save } from '../../api/deckApi';
import { useAuthContext } from '../../hooks';
import './style.css';
import { findById } from '../../api/userApi';

function Sidebar({ iconClass, ...props }) {
  const { user, login } = useAuthContext();
  const { show, setShow, scroll, backdrop } = props;
  // side bar toggle
  const handleClose = () => {
    setEditing(false);
    setShow(false);
  };
  const toggleShow = () => setShow((s) => !s);
  // error
  const [showError, setShowError] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  // Deck edit toggle
  const [editing, setEditing] = useState(false);
  const [newDeckTitle, setNewDeckTitle] = useState('');

  const handleEditClick = () => {
    setEditing(true);
    setShowError(false);
    setErrorMessage('');
  };

  const handleInputChange = (e) => {
    setNewDeckTitle(e.target.value);
  };

  const handleCreateClick = () => {
    // trim
    const trimmedTitle = newDeckTitle.trim();

    // check if title is empty
    if (!trimmedTitle) {
      setErrorMessage('Deck title cannot be empty');
      setShowError(true);
      return;
    }

    // create new deck object
    const newDeck = {
      userId: user.userId,
      deckName: trimmedTitle,
    };

    // add function
    save(newDeck)
      .then(() => {
        // sucess
        console.log('Deck saved successfully');
        findById(user.userId).then((data) => {
          login(data);
        });
        setShowError(false);
      })
      .catch((error) => {
        // error
        console.error('Error saving deck', error);
        setShowError(true);
        setErrorMessage('Error saving deck');
      })
      .finally(() => {
        // reset state
        setEditing(false);
        setNewDeckTitle('');
      });
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
      <Offcanvas
        className="sidebar-container"
        show={show}
        onHide={handleClose}
        scroll={scroll}
        backdrop={backdrop}
      >
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
              <>
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
              {user &&
                user.deckList.map((deck) => (
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
