import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import OrderGrid from "./OrderGrid";
import { useEffect, useState } from "react";
import getOrders from "../../services/orders/getOrders";
const OrderPage = () => {
  const [minOrderList, setMinOrderList] = useState([]);
  const [page, setPage] = useState(0);
  const token = JSON.parse(localStorage.getItem("token"));

  useEffect(() => {
    if (token !== null) {
      getOrders({
        query: [token, page, 5],
      }).then((response) => {
        setMinOrderList(response.data.minOrderList);
      });
    }
  }, [token, page]);

  const handlePreviousPage = () => {
    if (page > 0) {
      setPage((prevPage) => prevPage - 1);
    }
  };

  const handleNextPage = () => {
    setPage((prevPage) => prevPage + 1);
  };

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
        <h1 className="title-center">Orders</h1>
        <div className="button-container">
          <button className="previous-button" onClick={handlePreviousPage}>
            Previous
          </button>
          <span className="page-number">{page}</span>
          <button className="next-button" onClick={handleNextPage}>
            Next
          </button>
        </div>
        <OrderGrid minOrderList={minOrderList} />
      </div>
    );
  }
};
export default OrderPage;
