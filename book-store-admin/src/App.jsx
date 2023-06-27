import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from "./components/Auth/Register";
import { UserDetailProvider } from "./contexts/userDetailContext";
import SignIn from "./components/Auth/SignIn";
import Dashboard from "./components/Dashboard";
const App = () => {
  return (
    <BrowserRouter>
      <UserDetailProvider>
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<SignIn />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </UserDetailProvider>
    </BrowserRouter>
  );
};
const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
