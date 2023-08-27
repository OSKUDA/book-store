import axios from "axios";

const deleteBook = ({ query }) => {
  const token = query[0];
  const bookId = query[1];
  const url = `http://localhost:8080/api/v1/admin/book?id=${bookId}`;
  return axios
    .delete(url, {
      headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
    })
    .then((response) => response.data);
};
export default deleteBook;
