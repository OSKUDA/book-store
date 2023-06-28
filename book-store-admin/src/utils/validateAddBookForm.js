function validateAddBookForm(formData) {
  const title = formData.title;
  const author = formData.author;
  const publicationDate = formData.publicationDate;
  const quantity = formData.quantity;
  const price = formData.price;
  const summary = formData.summary;

  const resultObj = {};

  isValidString(title) ? (resultObj.title = true) : (resultObj.title = false);
  isValidString(author)
    ? (resultObj.author = true)
    : (resultObj.author = false);
  isValidPublicationDate(publicationDate)
    ? (resultObj.publicationDate = true)
    : (resultObj.publicationDate = false);
  isValidString(summary)
    ? (resultObj.summary = true)
    : (resultObj.summary = false);
  isValidNumber(quantity)
    ? (resultObj.quantity = true)
    : (resultObj.quantity = false);
  isValidNumber(price) ? (resultObj.price = true) : (resultObj.price = false);

  return resultObj;
}
function isValidString(str) {
  return str !== "";
}
function isValidNumber(num) {
  if (num > 0) {
    return true;
  }
  return false;
}
function isValidPublicationDate(str) {
  if (str.length !== 4) {
    return false;
  }
  if (!/^\d+$/.test(str)) {
    return false;
  }
  return true;
}
export default validateAddBookForm;
