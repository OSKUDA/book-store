import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from "./components/Auth/Register";
import { UserDetailProvider } from "./contexts/userDetailContext";
import SignIn from "./components/Auth/SignIn";
import Dashboard from "./components/Dashboard";
import ErrorBoundary from "./components/ErrorBoundry";
import Books from "./components/Book/BooksPage";
import BookDetails from "./components/Book/BookDetailsPage";
import Orders from "./components/Order/OrderPage";
import Profile from "./components/Profile/ProfilePage";
import User from "./components/Users/UserPage";
const App = () => {
  return (
    <ErrorBoundary>
      <BrowserRouter>
        <UserDetailProvider>
          <Routes>
            <Route path="/" element={<SignIn />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<SignIn />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/dashboard/books" element={<Books />} />
            <Route
              path="/dashboard/bookDetails/:bookId"
              element={<BookDetails />}
            />
            <Route path="/dashboard/orders" element={<Orders />} />
            <Route path="/dashboard/profile" element={<Profile />} />
            <Route path="/dashboard/users" element={<User />} />
          </Routes>
        </UserDetailProvider>
      </BrowserRouter>
    </ErrorBoundary>
  );
};
const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
