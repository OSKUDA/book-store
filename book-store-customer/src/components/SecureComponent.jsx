import { useEffect, useState } from "react";
import getSecureData from "../services/getSecureData";

const SecureComponent = () => {
  const [result, setResult] = useState("");
  useEffect(() => {
    getSecureData({
      query: [localStorage.getItem("token")],
    })
      .then((response) => {
        setResult(response.data);
      })
      .catch((e) => {
        console.error(e.message);
      });
  }, []);

  if (result === "") {
    return <h3>Blank</h3>;
  } else {
    return <h3>{result}</h3>;
  }
};
export default SecureComponent;
