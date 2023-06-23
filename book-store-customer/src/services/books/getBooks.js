import axios from "axios";

const getBooks = ({ query }) => {
  const token = query[0];
  const page = query[1];
  const length = query[2];
  const url = `http://localhost:8080/api/v1/books?page=${page}&length=${length}`;
  return axios.get(url, {
    headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
  });
};
export default getBooks;
