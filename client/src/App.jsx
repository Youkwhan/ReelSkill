import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { Home, Login } from './scenes';
import Navbar from './components/Navbar';

function App() {
  return (
    <Router>
      <Navbar />
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
