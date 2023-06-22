import { useState } from "react";
import { Link } from "react-router-dom";
import validateEmailPassword from "../utils/validateEmailPassword";
import getForgetPassword from "../services/getForgetPassword";
import validateResetPasswordUrl from "../utils/validateResetPasswordUrl";
import postResetPassword from "../services/postResetPassword";
const ForgotPassword = () => {
  const [emailError, setEmailError] = useState(false);
  const [urlError, setUrlError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [result, setResult] = useState("");
  const [serverError, setServerError] = useState(false);
  return (
    <div className="form-container vertical-center">
      <form
        onSubmit={(e) => {
          e.preventDefault();
          const formData = new FormData(e.target);
          const obj = {
            email: formData.get("email") ?? "",
            url: formData.get("reset-password-url") ?? "",
            password: formData.get("new-password") ?? "",
          };
          // input validation
          const validObj = {
            emailAndPassword: validateEmailPassword(obj.email, obj.password),
            resetUrl: validateResetPasswordUrl(obj.url),
          };
          setEmailError(!validObj.emailAndPassword.email);
          if (result !== "") {
            setPasswordError(!validObj.emailAndPassword.password);
            setUrlError(!validObj.resetUrl);
          }
          if (result === "" && validObj.emailAndPassword.email) {
            getForgetPassword({
              query: [obj.email],
            })
              .then((response) => {
                setResult(response.data);
              })
              .catch((e) => {
                console.error(e.message);
                setServerError(true);
              });
          }
          if (
            result !== "" &&
            validObj.emailAndPassword.email &&
            validObj.emailAndPassword.password &&
            validObj.resetUrl
          ) {
            postResetPassword({
              query: [obj.url, obj.password],
            })
              .then((response) => {
                setResult(response.data);
              })
              .catch((e) => {
                console.error(e.message);
                setServerError(true);
              });
          }
        }}
      >
        <h1 className="center">Reset Password</h1>
        <br />
        <br />
        <label htmlFor="email">
          Email:{" "}
          {emailError ? (
            <span className="error-message">* email is invalid</span>
          ) : null}
          <input name="email" id="email" placeholder="email" />
        </label>
        {result !== "" &&
        (result["forgetPasswordEmailStatus"] === "SENT" ||
          result["resetPasswordResponseStatus"]) ? (
          <div>
            <label htmlFor="reset-password-url">
              Reset url:{" "}
              {urlError ? (
                <span className="error-message">* malformed url</span>
              ) : null}
              <input
                name="reset-password-url"
                id="reset-password-url"
                placeholder="reset password url"
              />
            </label>
            <label htmlFor="new-password">
              New Password:{" "}
              {passwordError ? (
                <span className="error-message">* password invalid</span>
              ) : null}
              <input
                name="new-password"
                type="password"
                id="new-password"
                placeholder="password"
              />
            </label>
          </div>
        ) : null}

        <div className="button center">
          <button>Reset</button>
        </div>
        <br />
        {serverError ? (
          <span className="error-message">* server error</span>
        ) : null}
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
      <p className="server-message">
        {result === ""
          ? null
          : `Forgot password: ${
              result["forgetPasswordEmailStatus"]
                ? result["forgetPasswordEmailStatus"]
                : result["resetPasswordResponseStatus"]
            }`}
      </p>
    </div>
  );
};

export default ForgotPassword;
