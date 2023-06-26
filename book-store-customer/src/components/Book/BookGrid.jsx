import Book from "./Book";

const BookGrid = ({ minBookList }) => {
  return (
    <div className="book-container">
      <div className="book-grid">
        {minBookList.map((book) => (
          <Book key={book.id} minBook={book} />
        ))}
      </div>
    </div>
  );
};

export default BookGrid;
