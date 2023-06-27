import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import { CartContext } from "../../contexts/CartContext";
import { UserDetailContext } from "../../contexts/UserDetailContext";
import { useContext, useEffect, useState } from "react";
import getBook from "../../services/books/getBook";
import BookGrid from "../Book/BookGrid";
import postAddOrder from "../../services/orders/postAddOrder";
const CartPage = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  const { cartItems, clearCart } = useContext(CartContext);
  const [minBookList, setMinBookList] = useState([]);
  const [status, setStatus] = useState(false);
  const [deliveryAddress, setDeliveryAddress] = useState("");
  const [orderPostSuccess, setOrderPostSuccess] = useState(false);
  const { userDetail } = useContext(UserDetailContext);
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

  const handleAddOrder = (cartItems) => {
    postAddOrder({
      query: [token, userDetail.email, cartItems, deliveryAddress],
    })
      .then(() => {
        setOrderPostSuccess(true);
      })
      .catch(() => {
        setOrderPostSuccess(false);
      });
  };

  const handleDeliveryAddressChange = (event) => {
    setDeliveryAddress(event.target.value);
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
              <div className="cart-page-label-container">
                <label htmlFor="deliveryAddress" className="cart-page-label">
                  Delivery Address:
                </label>
                <input
                  type="email"
                  id="email"
                  className="cart-page-input"
                  value={deliveryAddress}
                  onChange={handleDeliveryAddressChange}
                />
              </div>
              <button
                className="confirm-order-button"
                onClick={() => {
                  if (deliveryAddress.trim() !== "") {
                    handleAddOrder(cartItems);
                    clearCart();
                  }
                }}
              >
                Confirm Order ✔️
              </button>
              <br />
              <br />
              {orderPostSuccess ? (
                <p className="cart-page-order-success-message">
                  Order placed successfully 🎉
                </p>
              ) : (
                <br />
              )}
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
