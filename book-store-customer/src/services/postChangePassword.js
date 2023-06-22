import axios from "axios";

const postChangePassword = ({ query }) => {
  const url = "http://localhost:8080/api/v1/auth/changePassword";
  const token = query[0];
  const email = query[1];
  const oldPassword = query[2];
  const newPassword = query[3];
  return axios.post(
    url,
    {
      email: email,
      oldPassword: oldPassword,
      newPassword: newPassword,
    },
    {
      headers: { Authorization: `Bearer ${token.replace(/^"(.*)"$/, "$1")}` },
    }
  );
};
export default postChangePassword;
