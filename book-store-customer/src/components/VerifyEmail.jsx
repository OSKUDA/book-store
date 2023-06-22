import { useState } from "react";
import { Link } from "react-router-dom";
import validateEmailVerificationUrl from "../utils/validateEmailVerificationUrl";
import getVerifyEmail from "../services/getVerifyEmail";
const VerifyEmail = () => {
  const [urlError, setUrlError] = useState(false);
  const [serverError, setServerError] = useState(false);
  const [result, setResult] = useState("");
  return (
    <div className="form-container vertical-center">
      <form
        onSubmit={async (e) => {
          e.preventDefault();
          const formData = new FormData(e.target);
          const obj = {
            url: formData.get("verification-url") ?? "",
          };
          // input validation
          const validObj = {
            url: validateEmailVerificationUrl(obj.url),
          };
          setUrlError(!validObj.url);
          if (validObj.url) {
            getVerifyEmail(obj.url)
              .then((response) => {
                setServerError(false);
                setResult(response.data);
              })
              .catch((e) => {
                console.error("ERROR: ", e.message);
                setServerError(true);
              });
          }
        }}
      >
        <h1 className="center">Verify Email</h1>
        <br />
        <br />

        <label htmlFor="verification-url">
          Verification URL{" "}
          {urlError ? (
            <span className="error-message">* malformed url</span>
          ) : null}
          {serverError ? (
            <span className="error-message">* server error</span>
          ) : null}
          <input
            name="verification-url"
            id="verification-url"
            placeholder="paste your verification link"
          />
        </label>

        <div className="button center">
          <button>Verify</button>
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
      <p className="server-message">
        {result === ""
          ? null
          : `Your email is: ${result["emailVerificationTokenStatus"]}`}
      </p>
    </div>
  );
};

export default VerifyEmail;
