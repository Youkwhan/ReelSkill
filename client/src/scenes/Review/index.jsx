import { useParams } from 'react-router-dom';
import ReviewHeader from './ReviewHeader';
import './style.css';
import { useEffect, useState } from 'react';
import { findById } from '../../api/deckApi';

function Review() {
  const { deckId } = useParams();
  const [deck, setDeck] = useState(null);
  console.log(deck);

  useEffect(() => {
    if (deckId) {
      findById(deckId).then((data) => {
        setDeck(data);
      });
    }
  }, [deckId]);

  return (
    <div>
      <header className="review-header mt-3">
        <span>{`review/${deckId}`}</span>
        <div className="review-filter-btn">
          <button>Review</button> {' / '}
          <button>Random</button> {' / '}
          <button>New</button>
        </div>
        <i className="bi bi-arrow-counterclockwise"></i>
      </header>
      <main className=" container">
        <ReviewHeader deck={deck} setDeck={setDeck} />
      </main>
    </div>
  );
}

export default Review;
