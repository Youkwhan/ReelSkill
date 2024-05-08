import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from 'react-router-dom';
import { Home, Login, Review } from './scenes';
import { useAuthContext } from './hooks';
import Nav from './components/Nav';
import { useState } from 'react';

function App() {
  const { user } = useAuthContext();
  const [show, setShow] = useState(false);

  return (
    <Router>
      <Nav show={show} setShow={setShow} />
      <main className="container">
        <Routes>
          <Route
            path="/"
            element={
              user ? <Home setShow={setShow} /> : <Navigate to="/login" />
            }
          />
          <Route path="/review" element={<Review />} />
          <Route path="/login" element={<Login />} />
          <Route />
        </Routes>
      </main>
    </Router>
  );
}

export default App;
