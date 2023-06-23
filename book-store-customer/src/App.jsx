import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SignIn from "./components/SignIn";
import Register from "./components/Register";
// import ForgotPassword from "./components/ForgotPassword";
// import VerifyEmail from "./components/VerifyEmail";
import Dashboard from "./components/Dashboard";
import Books from "./components/BooksPage";
import BookDetailsPage from "./components/BookDetailsPage";
import { CartProvider } from "./contexts/CartContext";
import Cart from "./components/CartPage";
// import ChangePassword from "./components/ChangePassword";

const App = () => {
  return (
    <BrowserRouter>
      <CartProvider>
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/login" element={<SignIn />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/dashboard/books" element={<Books />} />
          <Route path="/dashboard/cart" element={<Cart />} />
          <Route
            path="/dashboard/bookDetails/:bookId"
            element={<BookDetailsPage />}
          />
        </Routes>
      </CartProvider>
    </BrowserRouter>
  );
};
const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
