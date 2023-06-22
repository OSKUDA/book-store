function validateFirstAndLastName(firstName, lastName) {
  return {
    firstName: isStringEmpty(firstName),
    lastName: isStringEmpty(lastName),
  };
}

function isStringEmpty(value) {
  return value;
}

export default validateFirstAndLastName;
