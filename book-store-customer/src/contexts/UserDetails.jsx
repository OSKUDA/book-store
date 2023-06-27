import React, { createContext, useState } from "react";
export const UserDetailContext = createContext();

export const UserDetailProvider = ({ children }) => {
  const [userDetail, setUserDetail] = useState({
    id: null,
    email: "",
    firstName: "",
    lastName: "",
    role: "",
  });

  const updateUserDetail = (newUserDetail) => {
    setUserDetail(newUserDetail);
  };

  return (
    <UserDetailContext.Provider value={{ userDetail, updateUserDetail }}>
      {children}
    </UserDetailContext.Provider>
  );
};
