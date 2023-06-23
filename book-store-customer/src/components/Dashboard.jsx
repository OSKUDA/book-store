import { Link } from "react-router-dom";
import NavBar from "./NavBar";

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
        <NavBar />
      </div>
    );
  }
};
export default Dashboard;
