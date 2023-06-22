function validateEmailVerificationUrl(url) {
  return url.startsWith("http://localhost:8080/api/v1/auth/verifyEmail?token=");
}

export default validateEmailVerificationUrl;
