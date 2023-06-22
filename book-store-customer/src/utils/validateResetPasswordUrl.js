function validateResetPasswordUrl(url) {
  return url.startsWith(
    "http://localhost:8080/api/v1/auth/resetPassword?token="
  );
}

export default validateResetPasswordUrl;
