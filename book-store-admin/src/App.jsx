import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import RootPage from "./components/RootPage";
const App = () => {
  return (
    <BrowserRouter>
          <Routes>
            <Route path="/" element={<RootPage />} />
          </Routes>
    </BrowserRouter>
  );
};
const container = document.getElementById("root");
const root = createRoot(container);
root.render(<App />);
