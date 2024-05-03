import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

import Sidebar from './Sidebar';

function Nav() {
  const scrollOption = {
    name: '>>',
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
            Signed in as: <a href="#login">Mark Otto</a>
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Nav;
