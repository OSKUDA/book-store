import axios from "axios";
const postAuthenticate = ({ query }) => {
  const url = "http://localhost:8080/api/v1/authenticate";
  const email = query[0];
  const password = query[1];
  return axios.post(url, {
    email: email,
    password: password,
  });
};

export default postAuthenticate;
