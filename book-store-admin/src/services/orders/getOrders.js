import axios from "axios";
const getOrders = ({ query }) => {
  const token = query[0];
  const page = query[1];
  const length = query[2];
  const url = `http://localhost:8080/api/v1/admin/orders?page=${page}&length=${length}`;
  return axios
    .get(url, {
      headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
    })
    .then((response) => response.data);
};
export default getOrders;
