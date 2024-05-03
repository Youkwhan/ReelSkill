import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { Home, Login } from './scenes';
import Nav from './components/Nav';

function App() {
  return (
    <Router>
      <Nav />
      <main className="container">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </main>
    </Router>
  );
}

export default App;
