import { Link } from "react-router-dom";
import NavBar from "./NavBar";
import BookGrid from "./BookGrid";
const Books = () => {
  const token = JSON.parse(localStorage.getItem("token"));

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
    const minBookList = [
      {
        id: 1,
        title: "Book 1",
        author: "Author 1",
        publicationDate: 1990,
        quantity: 20,
        price: 2000,
      },
      // Add more books here...
    ];
    return (
      <div>
        <NavBar />
        <h1 className="title-center">Books</h1>
        <BookGrid minBookList={minBookList} />
      </div>
    );
  }
};
export default Books;
