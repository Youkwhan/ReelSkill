import { useLocation } from 'react-router-dom';
import ReviewHeader from './ReviewHeader';
import './style.css';
import { useEffect, useState } from 'react';
import { findById } from '../../api/deckApi';

function Review() {
  const location = useLocation();
  const { deckId } = location.state;
  const [deck, setDeck] = useState(null);

  useEffect(() => {
    findById(deckId).then((data) => {
      setDeck(data);
      console.log(deck);
    });
  }, [deckId]);
  return (
    <div>
      <header className="review-header">
        <span>{`review/${deckId}`}</span>
        <div className="review-filter-btn">
          <button>Review</button> {' / '}
          <button>Random</button> {' / '}
          <button>New</button>
        </div>
        <i className="bi bi-arrow-counterclockwise"></i>
      </header>
      <main>
        <ReviewHeader deck={deck} />
      </main>
    </div>
  );
}

export default Review;
