import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from 'react-router-dom';
import { Home, Login } from './scenes';
import { useAuthContext } from './hooks';
import Nav from './components/Nav';

function App() {
  const { user } = useAuthContext();

  return (
    <Router>
      <Nav />
      <main className="container">
        <Routes>
          <Route
            path="/"
            element={user ? <Home /> : <Navigate to="/login" />}
          />
          <Route path="/login" element={<Login />} />
          <Route />
        </Routes>
      </main>
    </Router>
  );
}

export default App;
