import { Link } from "react-router-dom";

const Dashboard = () => {
  const token = JSON.parse(localStorage.getItem("token"));
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
        <div className="navbar">
          <div className="navbar-title">BookStore.com</div>
          <Link to={"/dashboard"}>Books</Link>
          <Link to={"/dashboard"}>Orders</Link>
          <Link to={"/dashboard"}>Profile</Link>
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
  }
};
export default Dashboard;
