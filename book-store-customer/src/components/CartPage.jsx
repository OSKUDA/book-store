import { Link } from "react-router-dom";
import NavBar from "./NavBar";
import { CartContext } from "../contexts/CartContext";
import { useContext, useEffect, useState } from "react";
import getBook from "../services/books/getBook";
import BookGrid from "./BookGrid";
const CartPage = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  const { cartItems, clearCart } = useContext(CartContext);
  const [minBookList, setMinBookList] = useState([]);
  const [status, setStatus] = useState(false);

  useEffect(() => {
    const fetchBooks = async () => {
      const bookData = [];
      for (const id of cartItems) {
        const response = await getBook({
          query: [token, id],
        });
        bookData.push(response.data.book);
      }
      setMinBookList(bookData);
    };

    fetchBooks();
    setStatus(true);
  }, [token, cartItems]);

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
        </form>
      </div>
    );
  } else {
    return (
      <div>
        <NavBar />
        {status ? (
          <div>
            <div className="cart-page-button-container">
              <h1 className="title-center">Cart</h1>
              <button
                className="confirm-order-button"
                onClick={() => {
                  clearCart();
                }}
              >
                Confirm Order ✔️
              </button>
            </div>
            <BookGrid minBookList={minBookList} />
          </div>
        ) : (
          <h2>Failed</h2>
        )}
      </div>
    );
  }
};
export default CartPage;
