/* eslint-disable jsx-a11y/label-has-associated-control */
const BookDetailsCard = ({ book }) => {
  return (
    <div className="book-details-container">
      <div className="book-details">
        <div className="book-details-field">
          <label className="book-details-label">Book ID:</label>
          <span className="book-details-span" id="book-id">
            {book.id}
          </span>
        </div>
        <div className="book-details-field">
          <label className="book-details-label">Title:</label>
          <span className="book-details-span" id="title">
            {book.title}
          </span>
        </div>
        <div className="book-details-field">
          <label className="book-details-label">Author:</label>
          <span className="book-details-span" id="author">
            {book.author}
          </span>
        </div>
        <div className="book-details-field">
          <label className="book-details-label">Publication Date:</label>
          <span className="book-details-span" id="publication-date">
            {book.publicationDate}
          </span>
        </div>
        <div className="book-details-field">
          <label className="book-details-label">Summary:</label>
          <span className="book-details-span" id="summary">
            {book.summary}
          </span>
        </div>
        <div className="book-details-field">
          <label className="book-details-label">Quantity:</label>
          <span className="book-details-span" id="quantity">
            {book.quantity}
          </span>
        </div>
        <div className="book-details-field">
          <label className="book-details-label">Price:</label>
          <span className="book-details-span" id="price">
            ₹{book.price}
          </span>
        </div>
      </div>
    </div>
  );
};
export default BookDetailsCard;
