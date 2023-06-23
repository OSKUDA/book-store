import axios from "axios";
const postResetPassword = ({ query }) => {
  const url = query[0];
  const password = query[1];
  return axios.post(url, {
    password: password,
  });
};

export default postResetPassword;
