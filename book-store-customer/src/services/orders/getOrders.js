import axios from "axios";
const getOrders = ({ query }) => {
  const token = query[0];
  const page = 0;
  const length = 50;
  const url = `http://localhost:8080/api/v1/orders?page=${page}&length=${length}`;
  return axios.get(url, {
    headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
  });
};
export default getOrders;
