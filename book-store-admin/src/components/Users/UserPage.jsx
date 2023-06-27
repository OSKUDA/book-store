import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import UserGrid from "./UserGrid";
import { useEffect, useState } from "react";
import getUsers from "../../services/users/getUsers";
const UserPage = () => {
  const [minUserList, setMinUserList] = useState([]);
  const token = JSON.parse(localStorage.getItem("token"));

  useEffect(() => {
    if (token !== null) {
      getUsers({
        query: [token],
      }).then((response) => {
        setMinUserList(response.data.minUserList);
      });
    }
  }, [token]);

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
        <h1 className="title-center">Users</h1>
        <UserGrid minUserList={minUserList} />
      </div>
    );
  }
};
export default UserPage;
