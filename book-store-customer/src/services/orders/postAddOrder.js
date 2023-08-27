import axios from "axios";

const postAddOrder = ({ query }) => {
  const token = query[0];
  const email = query[1];
  const bookIdList = query[2];
  const deliveryAddress = query[3];
  const url = "http://localhost:8080/api/v1/order";
  return axios
    .post(
      url,
      {
        email: email,
        bookIdList: bookIdList,
        deliveryAddress: deliveryAddress,
      },
      {
        headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
      }
    )
    .then((response) => response.data);
};
export default postAddOrder;
