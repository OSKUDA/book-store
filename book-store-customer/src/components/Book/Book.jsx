import { useNavigate } from "react-router-dom";
const Book = ({ minBook }) => {
  const navigate = useNavigate();
  const handleCardClick = () => {
    navigate(`/dashboard/bookDetails/${minBook.id}`);
  };
  return (
    // eslint-disable-next-line jsx-a11y/click-events-have-key-events, jsx-a11y/no-static-element-interactions
    <div className="book-card" onClick={handleCardClick}>
      <h3 className="book-title">&quot;{minBook.title}&quot;</h3>
      <p className="book-author">— {minBook.author}</p>
      <p className="book-publication">year: {minBook.publicationDate}</p>
      <p className="book-price">₹{minBook.price}</p>
      <p className="book-amount">Available: {minBook.quantity}</p>
    </div>
  );
};
export default Book;
