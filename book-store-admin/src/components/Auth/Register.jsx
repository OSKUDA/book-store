import { useState } from "react";
import { Link } from "react-router-dom";
import validateEmailPassword from "../../utils/validateEmailPassword";
import validateFirstAndLastName from "../../utils/validateFirstAndLastName";
import postRegisterUser from "../../services/auth/postRegisterUser";
const Register = () => {
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [firstNameError, setFirstNameError] = useState(false);
  const [lastNameError, setLastNameError] = useState(false);
  const [result, setResult] = useState("");
  const [serverError, setServerError] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  return (
    <div className="form-container vertical-center">
      <form
        onSubmit={(e) => {
          e.preventDefault();
          setIsLoading(true);
          const formData = new FormData(e.target);
          const obj = {
            email: formData.get("email") ?? "",
            password: formData.get("password") ?? "",
            firstName: formData.get("firstName") ?? "",
            lastName: formData.get("lastName") ?? "",
          };
          // input validation
          const validObj = {
            emailAndPassword: validateEmailPassword(obj.email, obj.password),
            firstAndLastName: validateFirstAndLastName(
              obj.firstName,
              obj.lastName
            ),
          };
          setEmailError(!validObj.emailAndPassword.email);
          setPasswordError(!validObj.emailAndPassword.password);
          setFirstNameError(!validObj.firstAndLastName.firstName);
          setLastNameError(!validObj.firstAndLastName.lastName);
          if (
            validObj.emailAndPassword.email &&
            validObj.emailAndPassword.password
          ) {
            postRegisterUser({
              query: [obj.firstName, obj.lastName, obj.email, obj.password],
            })
              .then((response) => {
                setIsLoading(false);
                setServerError(false);
                setResult(response.data);
                console.log(result);
              })
              .catch((e) => {
                console.error(e.message);
                setIsLoading(false);
                setServerError(true);
              });
          } else {
            setIsLoading(false);
          }
        }}
      >
        <h1 className="app-title center">BookStore Admin</h1>
        <br />
        <br />
        <h1 className="center">Register</h1>
        <br />
        <br />
        <label htmlFor="firstName">
          First Name:{" "}
          {firstNameError ? (
            <span className="error-message">* first name is empty</span>
          ) : null}
          <input name="firstName" id="firstName" placeholder="first name" />
        </label>
        <label htmlFor="lastName">
          Last Name:{" "}
          {lastNameError ? (
            <span className="error-message">* last name is empty</span>
          ) : null}
          <input name="lastName" id="lastName" placeholder="last name" />
        </label>
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
        <div className="button center">
          <button>{isLoading ? "---" : "Login"}</button>
        </div>
        <br />
        {serverError ? (
          <span className="error-message">* server error {serverError}</span>
        ) : null}
        <br />
        <hr />
        <p>
          already have an account? <Link to={"/login"}>sign-in here</Link>
        </p>
      </form>
      <p className="server-message">
        {result === ""
          ? null
          : `Registration: ${
              result["registrationSuccess"] ? "success" : "email already exists"
            }`}
      </p>
    </div>
  );
};

export default Register;
