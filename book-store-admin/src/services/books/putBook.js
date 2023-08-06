import axios from "axios";
const putBook = ({ query }) => {
  const token = query[0];
  const bookId = query[1];
  const bookData = query[2];
  const url = `http://localhost:8080/api/v1/admin/book?id=${bookId}`;
  const title = bookData.title;
  const author = bookData.author;
  const publicationDate = bookData.publicationDate;
  const quantity = bookData.quantity;
  const price = bookData.price;
  const summary = bookData.summary;
  return axios
    .put(
      url,
      {
        title: title,
        author: author,
        publicationDate: publicationDate,
        summary: summary,
        quantity: quantity,
        price: price,
      },
      {
        headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
      }
    )
    .catch((e) => {
      throw new Error(e.message);
    });
};

export default putBook;
