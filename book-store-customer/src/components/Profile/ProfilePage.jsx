import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import { UserDetailContext } from "../../contexts/UserDetailContext";
import { useContext } from "react";
const ProfilePage = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  const { userDetail } = useContext(UserDetailContext);
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
        <h1 className="title-center">Profile</h1>
        <div className="profile-card">
          <p className="profile-id">uid: {userDetail.id}</p>
          <p className="profile-email">email: {userDetail.email}</p>
          <p className="profile-first-name">
            first name: {userDetail.firstName}
          </p>
          <p className="profile-last-name">last name: {userDetail.lastName}</p>
          <p className="profile-role">role: {userDetail.role}</p>
        </div>
      </div>
    );
  }
};
export default ProfilePage;
