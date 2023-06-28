import { Link } from "react-router-dom";
import NavBar from "../NavBar";
const AddBookPage = () => {
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
    return (
      <div>
        <NavBar />
        <h1 className="title-center">Add Book</h1>
        <div className="add-book-form-container">
          <form
            onSubmit={(e) => {
              console.log(e);
            }}
          >
            <h3 className="center">Add New Book Entry</h3>
            <br />
            <br />
            <label htmlFor="title">
              Title:{" "}
              {/* {emailError ? (
                <span className="error-message">* email is invalid</span>
              ) : null} */}
              <input name="title" id="title" placeholder="title" />
            </label>
            <label htmlFor="author">
              Author:{" "}
              {/* {passwordError ? (
                <span className="error-message">* password is invalid</span>
              ) : null} */}
              <input
                name="author"
                type="text"
                id="author"
                placeholder="author"
              />
            </label>
            <label htmlFor="publicationDate">
              Publication Year:{" "}
              {/* {passwordError ? (
                <span className="error-message">* password is invalid</span>
              ) : null} */}
              <input
                name="publicationDate"
                type="number"
                id="publicationDate"
                placeholder="publication date (YYYY)"
              />
            </label>
            <label htmlFor="quantity">
              Quantity:{" "}
              {/* {passwordError ? (
                <span className="error-message">* password is invalid</span>
              ) : null} */}
              <input
                name="quantity"
                type="number"
                id="quantity"
                placeholder="quantity"
              />
            </label>
            <label htmlFor="price">
              Price:{" "}
              {/* {passwordError ? (
                <span className="error-message">* password is invalid</span>
              ) : null} */}
              <input
                name="price"
                type="number"
                id="price"
                placeholder="price"
              />
            </label>
            <label htmlFor="summary">
              Summary:{" "}
              {/* {passwordError ? (
                <span className="error-message">* password is invalid</span>
              ) : null} */}
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
