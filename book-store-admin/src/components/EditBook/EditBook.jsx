import { useState } from "react";
import validateAddBookForm from "../../utils/validateAddBookForm";
import putBook from "../../services/books/putBook";
import { useParams } from "react-router-dom";
const EditBookComponent = ({ book }) => {
  const { bookId } = useParams();

  const [title, setTitle] = useState(book.title);
  const [author, setAuthor] = useState(book.author);
  const [publicationDate, setPublicationDate] = useState(
    book.publicationDate.toString()
  );
  const [summary, setSummary] = useState(book.summary);
  const [quantity, setQuantity] = useState(book.quantity);
  const [price, setPrice] = useState(book.price);
  const [titleError, setTitleError] = useState(false);
  const [authorError, setAuthorError] = useState(false);
  const [publicationDateError, setPublicationDateError] = useState(false);
  const [quantityError, setQuantityError] = useState(false);
  const [priceError, setPriceError] = useState(false);
  const [summaryError, setSummaryError] = useState(false);
  const [serverError, setServerError] = useState(false);
  const [serverErrorMessage, setServerErrorMessage] = useState("");
  const [success, setSuccess] = useState(false);

  const token = JSON.parse(localStorage.getItem("token"));

  const handleSubmit = (event) => {
    event.preventDefault();
    // input validation
    const obj = {
      title: title,
      author: author,
      publicationDate: publicationDate,
      quantity: quantity,
      price: price,
      summary: summary,
    };
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
      putBook({
        query: [token, bookId, obj],
      })
        .then(() => {
          setSuccess(true);
          setServerError(false);
          setServerErrorMessage("");
        })
        .catch((e) => {
          setSuccess(false);
          setServerError(true);
          setServerErrorMessage(e.message);
        });
    }
  };

  return (
    <div className="book-details-container">
      <form className="book-details" onSubmit={handleSubmit}>
        <div className="book-details-field">
          <label className="book-details-label" htmlFor="title">
            Title:
          </label>
          <input
            type="text"
            id="title"
            className="book-details-input"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div className="book-details-field">
          <label className="book-details-label" htmlFor="author">
            Author:
          </label>
          <input
            type="text"
            id="author"
            className="book-details-input"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
          />
        </div>
        <div className="book-details-field">
          <label className="book-details-label" htmlFor="publication-date">
            Publication Date:
          </label>
          <input
            type="text"
            id="publication-date"
            className="book-details-input"
            value={publicationDate}
            onChange={(e) => setPublicationDate(e.target.value)}
          />
        </div>
        <div className="book-details-field">
          <label className="book-details-label" htmlFor="summary">
            Summary:
          </label>
          <textarea
            id="summary"
            className="book-details-input book-details-summary-input"
            value={summary}
            onChange={(e) => setSummary(e.target.value)}
          />
        </div>
        <div className="book-details-field">
          <label className="book-details-label" htmlFor="quantity">
            Quantity:
          </label>
          <input
            type="number"
            id="quantity"
            className="book-details-input"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
          />
        </div>
        <div className="book-details-field">
          <label className="book-details-label" htmlFor="price">
            Price:
          </label>
          <input
            type="number"
            id="price"
            className="book-details-input"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
        </div>
        <div className="book-details-button">
          <button type="submit" className="book-details-submit-button">
            Submit
          </button>
        </div>
      </form>
    </div>
  );
};

export default EditBookComponent;
