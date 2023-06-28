import { Link, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import NavBar from "../NavBar";
import getBook from "../../services/books/getBook";
import EditBook from "./EditBook";
const EditBookPage = () => {
  const { bookId } = useParams();
  const [book, setBook] = useState();
  const [status, setStatus] = useState(false);
  const token = JSON.parse(localStorage.getItem("token"));

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
        <h1 className="title-center">Edit Book</h1>
        {status ? (
          <div>
            <EditBook book={book} />
          </div>
        ) : (
          <p>Unable to load book...</p>
        )}
      </div>
    );
  }
};
export default EditBookPage;
