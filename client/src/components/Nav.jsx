import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

import Sidebar from './Sidebar';
import { useAuthContext, useDarkMode } from '../hooks';
import { Link } from 'react-router-dom';

function Nav({ show, setShow }) {
  const { user, logout } = useAuthContext();
  const handleDarkMode = useDarkMode();
  const scrollOption = {
    iconClass: 'bi bi-arrow-right-square',
    scroll: true,
    backdrop: false,
    show,
    setShow,
  };

  function handleLogout(evt) {
    evt.preventDefault();
    logout();
  }

  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        {user && <Sidebar {...scrollOption} />}
        <Navbar.Brand as={Link} to="/">
          ReelSkill
        </Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
            Signed in as: <Link to="/">{user ? user.username : 'Guest'}</Link>
          </Navbar.Text>
          <div className="d-flex align-items-center">
            <div className="ms-3 me-3">
              {user && (
                <Link
                  to="/login"
                  className="btn btn-danger btn-sm"
                  onClick={handleLogout}
                >
                  Log out
                </Link>
              )}
            </div>
            <div className="form-check form-switch">
              <input
                className="form-check-input"
                type="checkbox"
                role="switch"
                id="switchDarkMode"
                onChange={handleDarkMode}
              />
              <label
                className="form-check-label"
                htmlFor="switchDarkMode"
              ></label>
            </div>
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Nav;
