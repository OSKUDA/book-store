const Book = ({ minBook }) => {
  return (
    <div className="book-card">
      <h3 className="book-title">{minBook.title}</h3>
      <p className="book-author">{minBook.author}</p>
      <p className="book-publication">{minBook.publicationDate}</p>
      <p className="book-price">₹{minBook.price}</p>
      <p className="book-amount">Available: {minBook.quantity}</p>
    </div>
  );
};
export default Book;
