function validateEmailPassword(email = "", password = "", password1 = "") {
  return {
    email: validateEmail(email),
    password: validatePassword(password),
    password1: validatePassword(password1),
  };
}

function validateEmail(input) {
  var validRegex =
    /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  if (input.match(validRegex)) {
    return true;
  } else {
    return false;
  }
}

function validatePassword(input) {
  var passw = /^[A-Za-z]\w{7,14}$/;
  if (input.match(passw)) {
    return true;
  } else {
    return false;
  }
}

export default validateEmailPassword;
