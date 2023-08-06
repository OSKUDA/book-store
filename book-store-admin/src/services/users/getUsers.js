import axios from "axios";
const getUsers = ({ query }) => {
  const token = query[0];
  const url = `http://localhost:8080/api/v1/admin/users`;
  return axios.get(url, {
    headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
  });
};
export default getUsers;
