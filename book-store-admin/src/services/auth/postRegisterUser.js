import axios from "axios";
const postRegisterUser = ({ query }) => {
  const url = "http://localhost:8080/api/v1/register";
  const firstName = query[0];
  const lastName = query[1];
  const email = query[2];
  const password = query[3];
  return axios.post(url, {
    firstName: firstName,
    lastName: lastName,
    email: email,
    password: password,
    role: "ADMIN",
  });
};

export default postRegisterUser;
