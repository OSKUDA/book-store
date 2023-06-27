import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SignIn from "./components/Auth/SignIn";
import Register from "./components/Auth/Register";
// import ForgotPassword from "./components/ForgotPassword";
// import VerifyEmail from "./components/VerifyEmail";
import Dashboard from "./components/Dashboard";
import Books from "./components/Book/BooksPage";
import BookDetailsPage from "./components/Book/BookDetailsPage";
import { CartProvider } from "./contexts/CartContext";
import Cart from "./components/Cart/CartPage";
import Order from "./components/Order/OrderPage";
import { UserDetailProvider } from "./contexts/UserDetailContext";
// import ChangePassword from "./components/ChangePassword";

const App = () => {
  return (
    <BrowserRouter>
      <UserDetailProvider>
        <CartProvider>
          <Routes>
            <Route path="/" element={<SignIn />} />
            <Route path="/login" element={<SignIn />} />
            <Route path="/register" element={<Register />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/dashboard/books" element={<Books />} />
            <Route path="/dashboard/cart" element={<Cart />} />
            <Route path="/dashboard/orders" element={<Order />} />
            <Route
              path="/dashboard/bookDetails/:bookId"
              element={<BookDetailsPage />}
            />
          </Routes>
        </CartProvider>
      </UserDetailProvider>
    </BrowserRouter>
  );
};
const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
