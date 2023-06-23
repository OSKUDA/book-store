import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SignIn from "./components/SignIn";
import Register from "./components/Register";
// import ForgotPassword from "./components/ForgotPassword";
// import VerifyEmail from "./components/VerifyEmail";
import Dashboard from "./components/Dashboard";
import Books from "./components/BooksPage";
// import ChangePassword from "./components/ChangePassword";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<SignIn />} />
        <Route path="/login" element={<SignIn />} />
        <Route path="/register" element={<Register />} />
        {/* <Route path="/forgot-password" element={<ForgotPassword />} /> */}
        {/* <Route path="/verify-email" element={<VerifyEmail />} /> */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/dashboard/books" element={<Books />} />
        {/* <Route path="/change-password" element={<ChangePassword />} /> */}
      </Routes>
    </BrowserRouter>
  );
};
const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
