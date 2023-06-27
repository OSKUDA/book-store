import axios from "axios";
const getUser = ({ query }) => {
  const token = query[0];
  const email = query[1];
  const url = `http://localhost:8080/api/v1/user?email=${email}`;
  return axios.get(url, {
    headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
  });
};
export default getUser;
