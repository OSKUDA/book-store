import Order from "./Order";

const OrderGrid = ({ minOrderList }) => {
  return minOrderList == null || minOrderList.length == 0 ? null : (
    <div className="order-container">
      <div className="order-grid">
        {minOrderList.map((minOrder) => (
          <Order key={minOrder.id} minOrder={minOrder} />
        ))}
      </div>
    </div>
  );
};

export default OrderGrid;
