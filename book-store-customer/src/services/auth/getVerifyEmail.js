import axios from "axios";

const getVerifyEmail = (url) => {
  return axios.get(url);
};

export default getVerifyEmail;
