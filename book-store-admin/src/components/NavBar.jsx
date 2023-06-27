import { Link } from "react-router-dom";

const NavBar = () => {
  return (
    <div>
      <div className="navbar">
        <div className="navbar-title">
          <Link to={"/dashboard"}>BookStore.com</Link>
        </div>
        <Link to={"/dashboard/books"}>Books</Link>
        <Link to={"/dashboard/orders"}>Orders</Link>
        <Link to={"/dashboard/users"}>Users</Link>
        <Link to={"/dashboard/profile"}>Profile</Link>
        <Link
          to={"/"}
          onClick={() => {
            localStorage.removeItem("token");
          }}
        >
          Logout
        </Link>
      </div>
    </div>
  );
};
export default NavBar;
