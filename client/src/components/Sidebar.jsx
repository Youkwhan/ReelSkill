import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';

function Sidebar({ name, ...props }) {
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
        {name}
      </Button>
      <Offcanvas show={show} onHide={handleClose} {...props}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>ReelSkill</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body>
          <div>
            <div
              style={{
                display: 'flex',
                alignItems: 'center',
                borderBottom: '1px solid black',
                marginBottom: '10px',
              }}
            >
              <p style={{ marginBottom: '0', flex: '1' }}>Deck</p>
              {!editing && (
                <Button
                  variant="outline-primary"
                  size="sm"
                  onClick={handleEditClick}
                >
                  Edit
                </Button>
              )}
            </div>
            {editing && (
              <div
                style={{
                  display: 'flex',
                  alignItems: 'center',
                  marginTop: '10px',
                }}
              >
                <input
                  type="text"
                  value={newDeckTitle}
                  onChange={handleInputChange}
                />
                <Button
                  variant="success"
                  size="sm"
                  onClick={handleCreateClick}
                  style={{ marginLeft: '10px' }}
                >
                  Create
                </Button>
                <Button
                  variant="danger"
                  size="sm"
                  onClick={handleDeleteClick}
                  style={{ marginLeft: '10px' }}
                >
                  Delete
                </Button>
              </div>
            )}
            <div>
              <p>
                <a href="#">Deck link</a>
              </p>
              <p>
                <a href="#">Deck link</a>
              </p>
              <p>
                <a href="#">Deck link</a>
              </p>
            </div>
          </div>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}

export default Sidebar;
