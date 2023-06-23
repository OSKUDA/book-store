import { Link, useParams } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import NavBar from "./NavBar";
import BookDetailsCard from "./BookDetailsCard";
import getBook from "../services/books/getBook";
import { CartContext } from "../contexts/CartContext";
const BookDetailsPage = () => {
  const { bookId } = useParams();
  const { cartItems, addToCart } = useContext(CartContext);
  const [book, setBook] = useState();
  const [status, setStatus] = useState(false);
  const token = JSON.parse(localStorage.getItem("token"));
  useEffect(() => {
    if (token !== null) {
      getBook({
        query: [token, bookId],
      }).then((response) => {
        setBook(response.data.book);
        setStatus(true);
      });
    }
  }, [token, bookId]);

  const handleAddToCart = (bookId) => {
    localStorage.setItem("cartItems", JSON.stringify([...cartItems, bookId]));
    addToCart(bookId);
  };
  if (token === null) {
    return (
      <div className="form-container vertical-center">
        <form>
          <h1>You are not authenticated. Token=&quot;&quot;</h1>
          <p>
            already have an account? <Link to={"/login"}>sign-in here</Link>
          </p>
          <p>
            don&apos;t have an account?{" "}
            <Link to={"/register"}>register here</Link>
          </p>
          summary
        </form>
      </div>
    ).center;
  } else {
    return (
      <div>
        <NavBar />
        {status ? (
          <div>
            <div className="book-details-button-container">
              <h1 className="book-details-title-center">Book Details</h1>
              <button
                className="add-to-cart-button"
                onClick={() => handleAddToCart(bookId)}
              >
                Add to cart 🛒
              </button>
            </div>
            <BookDetailsCard book={book} />
          </div>
        ) : (
          <p>Loading book...</p>
        )}
      </div>
    );
  }
};
export default BookDetailsPage;
