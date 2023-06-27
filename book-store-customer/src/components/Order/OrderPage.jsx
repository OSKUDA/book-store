import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import OrderGrid from "./OrderGrid";
import { useEffect, useState, useContext } from "react";
import { UserDetailContext } from "../../contexts/UserDetails";
import getOrders from "../../services/orders/getOrders";
const OrderPage = () => {
  const [minOrderList, setMinOrderList] = useState([]);
  const { userDetail } = useContext(UserDetailContext);
  const token = JSON.parse(localStorage.getItem("token"));
  useEffect(() => {
    if (token !== null) {
      getOrders({
        query: [token, userDetail.email],
      }).then((response) => {
        setMinOrderList(response.data.minOrderList);
      });
    }
  }, [token, userDetail]);
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
        <OrderGrid minOrderList={minOrderList} />
      </div>
    );
  }
};
export default OrderPage;
