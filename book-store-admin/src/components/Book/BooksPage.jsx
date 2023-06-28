import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import BookGrid from "./BookGrid";
import getBooks from "../../services/books/getBooks";
import { useState, useEffect } from "react";
const BooksPage = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  const [minBookList, setMinBookList] = useState([]);
  const [status, setStatus] = useState(false);
  const [page, setPage] = useState(0);
  useEffect(() => {
    if (token !== null) {
      getBooks({ query: [token, page, 25] }).then((response) => {
        setMinBookList(response.data.bookList);
        setStatus(true);
      });
    }
  }, [token, page]);
  const handlePreviousPage = () => {
    if (page > 0) {
      setPage((prevPage) => prevPage - 1);
    }
  };

  const handleNextPage = () => {
    setPage((prevPage) => prevPage + 1);
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
            <h1 className="title-center">Books</h1>
            <div className="add-book-button-container">
              <button className="add-book-button" onClick={() => {}}>
                Add Book 📖
              </button>
            </div>
            <div className="button-container">
              <button className="previous-button" onClick={handlePreviousPage}>
                Previous
              </button>
              <span className="page-number">{page}</span>
              <button className="next-button" onClick={handleNextPage}>
                Next
              </button>
            </div>
            <BookGrid minBookList={minBookList} />
          </div>
        ) : (
          <p>Loading books...</p>
        )}
      </div>
    );
  }
};
export default BooksPage;
