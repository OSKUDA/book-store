import { Link, useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import NavBar from "../NavBar";
import BookDetailsCard from "./BookDetailsCard";
import getBook from "../../services/books/getBook";
import deleteBook from "../../services/books/deleteBook";
const BookDetailsPage = () => {
  const { bookId } = useParams();
  const [book, setBook] = useState();
  const [status, setStatus] = useState(false);
  const token = JSON.parse(localStorage.getItem("token"));
  const navigate = useNavigate();
  useEffect(() => {
    if (token !== null) {
      getBook({
        query: [token, bookId],
      }).then((response) => {
        setBook(response.data.book);
        setStatus(true);
      });
    }
  }, [token, bookId]);

  const handleDeleteBook = () => {
    deleteBook({
      query: [token, bookId],
    });
    navigate("/dashboard/books");
  };
  const handleEditBook = () => {
    navigate(`/dashboard/edit-book/${bookId}`);
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
          summary
        </form>
      </div>
    ).center;
  } else {
    return (
      <div>
        <NavBar />
        {status ? (
          <div>
            <div className="book-details-button-container">
              <h1 className="book-details-title-center">Book Details</h1>
              <button
                className="edit-book-button"
                onClick={() => {
                  handleEditBook();
                }}
              >
                Edit Book ✍
              </button>
              <button
                className="delete-book-button"
                onClick={() => {
                  handleDeleteBook();
                }}
              >
                Delete Book 🗑️
              </button>
            </div>
            <BookDetailsCard book={book} />
          </div>
        ) : (
          <p>Unable to load book...</p>
        )}
      </div>
    );
  }
};
export default BookDetailsPage;
