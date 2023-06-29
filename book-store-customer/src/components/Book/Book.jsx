/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import { useContext } from "react";
import { CartContext } from "../../contexts/CartContext";
import { useNavigate } from "react-router-dom";
const Book = ({ minBook, isCartPage }) => {
  const navigate = useNavigate();
  const { removeFromCart } = useContext(CartContext);
  const handleCardClick = () => {
    navigate(`/dashboard/bookDetails/${minBook.id}`);
  };
  const handleRemoveFromCart = (event) => {
    event.stopPropagation();
    removeFromCart(minBook.id);
  };
  return (
    <div className="book-card" onClick={handleCardClick}>
      <h3 className="book-title">&quot;{minBook.title}&quot;</h3>
      <p className="book-author">— {minBook.author}</p>
      <p className="book-publication">year: {minBook.publicationDate}</p>
      <p className="book-price">₹{minBook.price}</p>
      <p className="book-amount">Available: {minBook.quantity}</p>
      {isCartPage ? (
        <button onClick={handleRemoveFromCart}>Remove</button>
      ) : null}
    </div>
  );
};
export default Book;
