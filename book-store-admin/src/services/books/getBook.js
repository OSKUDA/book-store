import axios from "axios";

const getBook = ({ query }) => {
  const token = query[0];
  const bookId = query[1];
  const url = `http://localhost:8080/api/v1/book?id=${bookId}`;
  return axios
    .get(url, {
      headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
    })
    .then((response) => response.data);
};
export default getBook;
