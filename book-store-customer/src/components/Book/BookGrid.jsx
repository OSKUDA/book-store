import Book from "./Book";

const BookGrid = ({ minBookList, isCartPage }) => {
  return (
    <div className="book-container">
      <div className="book-grid">
        {minBookList.map((book) => (
          <Book key={book.id} minBook={book} isCartPage={isCartPage} />
        ))}
      </div>
    </div>
  );
};

export default BookGrid;
