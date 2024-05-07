import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

import Sidebar from './Sidebar';
import { useAuthContext, useDarkMode } from '../hooks';

function Nav() {
  const { user } = useAuthContext();
  const handleDarkMode = useDarkMode();
  const scrollOption = {
    iconClass: 'bi bi-arrow-right-square',
    scroll: true,
    backdrop: false,
  };

  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Sidebar {...scrollOption} />
        <Navbar.Brand href="#home">ReelSkill</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
            Signed in as: <a href="#login">{user ? user.username : 'Guest'}</a>
          </Navbar.Text>
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
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Nav;
