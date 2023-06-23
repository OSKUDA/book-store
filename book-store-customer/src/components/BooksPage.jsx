import { Link } from "react-router-dom";
import NavBar from "./NavBar";
import BookGrid from "./BookGrid";
import getBooks from "../services/books/getBooks";
import { useState, useEffect } from "react";
const Books = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  const [minBookList, setMinBookList] = useState([]);
  const [status, setStatus] = useState(false);
  useEffect(() => {
    if (token !== null) {
      getBooks({ query: [token, 0, 10] }).then((response) => {
        setMinBookList(response.data.bookList);
        setStatus(true);
      });
    }
  }, [token]);
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
        <h1 className="title-center">Books</h1>
        {status ? (
          <BookGrid minBookList={minBookList} />
        ) : (
          <p>Loading books...</p>
        )}
      </div>
    );
  }
};
export default Books;
