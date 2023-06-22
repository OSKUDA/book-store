import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import validateEmailPassword from "../utils/validateEmailPassword";
import validateConfirmPassword from "../utils/validateConfirmPassword";
import postChangePassword from "../services/postChangePassword";
const ChangePassword = () => {
  const [emailError, setEmailError] = useState(false);
  const [oldPasswordError, setOldPasswordError] = useState(false);
  const [newPasswordError, setNewPasswordError] = useState(false);
  const [confirmNewPasswordError, setConfirmNewPasswordError] = useState(false);
  const [serverError, setServerError] = useState(false);
  const navigate = useNavigate();
  return (
    <div className="form-container vertical-center">
      <form
        onSubmit={async (e) => {
          e.preventDefault();
          setServerError(false);
          const formData = new FormData(e.target);
          const obj = {
            email: formData.get("email") ?? "",
            oldPassword: formData.get("old-password") ?? "",
            newPassword: formData.get("new-password") ?? "",
            confirmNewPassword: formData.get("confirm-new-password") ?? "",
          };
          // input validation
          const validObj = {
            emailAndPassword: validateEmailPassword(
              obj.email,
              obj.oldPassword,
              obj.newPassword
            ),
            confirmPasswordMatch: validateConfirmPassword(
              obj.newPassword,
              obj.confirmNewPassword
            ),
          };
          setEmailError(!validObj.emailAndPassword.email);
          setOldPasswordError(!validObj.emailAndPassword.password);
          setNewPasswordError(!validObj.emailAndPassword.password1);
          setConfirmNewPasswordError(!validObj.confirmPasswordMatch);
          if (
            !(
              emailError &&
              oldPasswordError &&
              newPasswordError &&
              confirmNewPasswordError
            )
          ) {
            // call api
            postChangePassword({
              query: [
                localStorage.getItem("token"),
                obj.email,
                obj.oldPassword,
                obj.newPassword,
              ],
            })
              .then((response) => {
                if (response.data["changeUserPasswordStatus"] === "SUCCESS") {
                  localStorage.removeItem("token");
                  navigate("/");
                } else {
                  setServerError(true);
                }
              })
              .catch((e) => {
                console.error(e.message);
                setServerError(true);
              });
          }
        }}
      >
        <h1 className="center">Change Password</h1>
        <br />
        {serverError ? (
          <span className="error-message">* server error</span>
        ) : null}
        <br />
        <label htmlFor="email">
          Email:{" "}
          {emailError ? (
            <span className="error-message">* email is invalid</span>
          ) : null}
          <input name="email" id="email" placeholder="email" />
        </label>
        <br />
        <label htmlFor="old-password">
          Old password{" "}
          {oldPasswordError ? (
            <span className="error-message">* password is empty</span>
          ) : null}
          <input
            name="old-password"
            type="password"
            id="old-password"
            placeholder="old password"
          />
        </label>
        <label htmlFor="new-password">
          New Password:{" "}
          {newPasswordError ? (
            <span className="error-message">* password empty</span>
          ) : null}
          <input
            name="new-password"
            type="password"
            id="new-password"
            placeholder="new password"
          />
        </label>
        <label htmlFor="confirm-new-password">
          Confirm New Password:{" "}
          {confirmNewPasswordError ? (
            <span className="error-message">* password doesn&apos;t match</span>
          ) : null}
          <input
            name="confirm-new-password"
            type="password"
            id="confirm-new-password"
            placeholder="confirm new password"
          />
        </label>
        <div className="button center">
          <button>Change</button>
        </div>
        <br />
        <br />
        <hr />
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
};
export default ChangePassword;
