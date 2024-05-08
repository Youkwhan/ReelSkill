import './style.css';

function Home({ setShow }) {
  const toggleShow = () => setShow((s) => !s);

  return (
    <div className="home">
      <div className="home-container">
        <div className="home-header">
          <h1>ReelSkill</h1>
          <h4>Mastering Knowledge, One Card at a Time!</h4>
        </div>
        <div className="home-description">
          <p>Select a Deck to start Learning</p>
          <button variant="link-dark" onClick={toggleShow} className="me-2">
            <i className="bi bi-arrow-right-square"></i>
          </button>
        </div>
      </div>
    </div>
  );
}

export default Home;
