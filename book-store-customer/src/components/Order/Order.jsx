import Book from "../Book/Book";
const Order = (minOrder) => {
  minOrder = minOrder.minOrder;
  return (
    <div className="order-card">
      <h3 className="order-number">#{minOrder.id}</h3>
      <p className="order-email">email: {minOrder.minUser.email}</p>
      <p>Books:</p>
      <div className="order-book-list">
        {minOrder.minBookList.map((minBook) => (
          <Book key={minBook.id} minBook={minBook} />
        ))}
      </div>
      <p className="order-total">Total: 450</p>
      <p className="order-delivery-address">Delivery address: Long address</p>
    </div>
  );
};
export default Order;
