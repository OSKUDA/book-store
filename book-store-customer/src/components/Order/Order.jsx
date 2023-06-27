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
      <p className="order-total">Total: ₹{minOrder.amount}</p>
      <p className="order-delivery-address">
        Delivery address: {minOrder.deliveryAddress}
      </p>
    </div>
  );
};
export default Order;
