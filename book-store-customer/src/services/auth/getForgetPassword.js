import axios from "axios";

const getForgetPassword = ({ query }) => {
  const email = query[0];
  const url = `http://localhost:8080/api/v1/auth/forgetPassword?email=${email}`;
  return axios.get(url);
};

export default getForgetPassword;
