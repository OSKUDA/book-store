import { Link } from "react-router-dom";
import { useState } from "react";
import NavBar from "../NavBar";
import postBook from "../../services/books/postBook";
import validateAddBookForm from "../../utils/validateAddBookForm";

const AddBookPage = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  const [titleError, setTitleError] = useState(false);
  const [authorError, setAuthorError] = useState(false);
  const [publicationDateError, setPublicationDateError] = useState(false);
  const [quantityError, setQuantityError] = useState(false);
  const [priceError, setPriceError] = useState(false);
  const [summaryError, setSummaryError] = useState(false);
  const [success, setSuccess] = useState(false);
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
        <h1 className="title-center">Add Book</h1>
        <div className="add-book-form-container">
          <form
            onSubmit={(e) => {
              e.preventDefault();
              const formData = new FormData(e.target);
              const obj = {
                title: formData.get("title") ?? "",
                author: formData.get("author") ?? "",
                publicationDate: formData.get("publicationDate") ?? 0,
                quantity: formData.get("quantity") ?? 0,
                price: formData.get("price") ?? 0,
                summary: formData.get("summary") ?? "",
              };
              // input validation
              const validObj = validateAddBookForm(obj);
              setTitleError(!validObj.title);
              setAuthorError(!validObj.author);
              setPublicationDateError(!validObj.publicationDate);
              setQuantityError(!validObj.quantity);
              setPriceError(!validObj.price);
              setSummaryError(!validObj.summary);
              if (
                validObj.title &&
                validObj.author &&
                validObj.publicationDate &&
                validObj.quantity &&
                validObj.price &&
                validObj.summary
              ) {
                console.log("post called");
                postBook({
                  query: [token, obj],
                });
              }
            }}
          >
            <h3 className="center">Add New Book Entry</h3>
            <br />
            <br />
            <label htmlFor="title">
              Title:{" "}
              {titleError ? (
                <span className="error-message">* title is invalid</span>
              ) : null}
              <input name="title" id="title" placeholder="title" />
            </label>
            <label htmlFor="author">
              Author:{" "}
              {authorError ? (
                <span className="error-message">* author is invalid</span>
              ) : null}
              <input
                name="author"
                type="text"
                id="author"
                placeholder="author"
              />
            </label>
            <label htmlFor="publicationDate">
              Publication Year:{" "}
              {publicationDateError ? (
                <span className="error-message">
                  * publication date is invalid
                </span>
              ) : null}
              <input
                name="publicationDate"
                type="number"
                id="publicationDate"
                placeholder="publication date (YYYY)"
              />
            </label>
            <label htmlFor="quantity">
              Quantity:{" "}
              {quantityError ? (
                <span className="error-message">* quantity is invalid</span>
              ) : null}
              <input
                name="quantity"
                type="number"
                id="quantity"
                placeholder="quantity"
              />
            </label>
            <label htmlFor="price">
              Price:{" "}
              {priceError ? (
                <span className="error-message">* price is invalid</span>
              ) : null}
              <input
                name="price"
                type="number"
                id="price"
                placeholder="price"
              />
            </label>
            <label htmlFor="summary">
              Summary:{" "}
              {summaryError ? (
                <span className="error-message">* summary is invalid</span>
              ) : null}
              <input
                name="summary"
                type="text"
                id="summary"
                placeholder="book summary..."
              />
            </label>
            <br />
            {/* {invalidCredentialError ? (
              <span className="error-message">* invalid credentials</span>
            ) : null} */}
            <div className="button center">
              <button>Add</button>
            </div>
            <br />
            {/* {serverError ? (
              <span className="error-message">
                * server error {serverError}
              </span>
            ) : null} */}
            <br />
            <hr />
          </form>
          {/* <p className="server-message">
            {result === ""
              ? null
              : `Authentication Status: ${result["token"] !== ""}`}
          </p> */}
        </div>
      </div>
    );
  }
};
export default AddBookPage;
