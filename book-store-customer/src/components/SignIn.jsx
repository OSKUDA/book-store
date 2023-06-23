import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import validateEmailPassword from "../utils/validateEmailPassword";
import postAuthenticate from "../services/auth/postAuthenticate";
const SignIn = () => {
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [result, setResult] = useState("");
  const [serverError, setServerError] = useState(false);
  const [invalidCredentialError, setInvalidCredentialError] = useState(false);
  // eslint-disable-next-line no-unused-vars
  const navigate = useNavigate();
  return (
    <div className="form-container vertical-center">
      <form
        onSubmit={(e) => {
          e.preventDefault();
          setInvalidCredentialError(false);
          setResult("");
          const formData = new FormData(e.target);
          const obj = {
            email: formData.get("email") ?? "",
            password: formData.get("password") ?? "",
          };
          // input validation
          const validObj = validateEmailPassword(obj.email, obj.password);
          setEmailError(!validObj.email);
          setPasswordError(!validObj.password);
          if (validObj.email && validObj.password) {
            postAuthenticate({
              query: [obj.email, obj.password],
            })
              .then((response) => {
                if (response.data["token"] === "not-valid") {
                  setInvalidCredentialError(true);
                } else {
                  setResult(response.data);
                  localStorage.setItem(
                    "token",
                    JSON.stringify(response.data["token"])
                  );
                  navigate("/dashboard");
                }
              })
              .catch((e) => {
                console.error(e);
                setServerError(true);
              });
          }
        }}
      >
        <h1 className="app-title center">BookStore</h1>
        <br />
        <br />
        <h3 className="center">Sign-In</h3>
        <br />
        <br />
        <label htmlFor="email">
          Email:{" "}
          {emailError ? (
            <span className="error-message">* email is invalid</span>
          ) : null}
          <input name="email" id="email" placeholder="email" />
        </label>
        <label htmlFor="password">
          Password:{" "}
          {passwordError ? (
            <span className="error-message">* password is invalid</span>
          ) : null}
          <input
            name="password"
            type="password"
            id="password"
            placeholder="password"
          />
        </label>
        <br />
        {invalidCredentialError ? (
          <span className="error-message">* invalid credentials</span>
        ) : null}
        <div className="button center">
          <button>Login</button>
        </div>
        <br />
        {serverError ? (
          <span className="error-message">* server error {serverError}</span>
        ) : null}
        <br />
        <hr />
        {/* <p>
          forgot password? <Link to={"/forgot-password"}>click here</Link>
        </p> */}
        <p>
          don&apos;t have an account?{" "}
          <Link to={"/register"}>register here</Link>
        </p>
      </form>
      <p className="server-message">
        {result === ""
          ? null
          : `Authentication Status: ${result["token"] !== ""}`}
      </p>
    </div>
  );
};

export default SignIn;
