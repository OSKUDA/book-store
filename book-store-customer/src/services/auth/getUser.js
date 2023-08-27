import axios from "axios";
const getUser = ({ query }) => {
  const token = query[0];
  const url = `http://localhost:8080/api/v1/user`;
  return axios
    .get(url, {
      headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
    })
    .then((response) => response.data);
};
export default getUser;
