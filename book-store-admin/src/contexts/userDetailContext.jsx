import React, { createContext, useState, useEffect } from "react";
export const UserDetailContext = createContext();

export const UserDetailProvider = ({ children }) => {
  const [userDetail, setUserDetail] = useState(() => {
    const storedUserDetail = localStorage.getItem("userDetail");
    return storedUserDetail
      ? JSON.parse(storedUserDetail)
      : {
          id: null,
          email: "",
          firstName: "",
          lastName: "",
          role: "",
        };
  });

  useEffect(() => {
    localStorage.setItem("userDetail", JSON.stringify(userDetail));
  }, [userDetail]);

  const updateUserDetail = (newUserDetail) => {
    setUserDetail(newUserDetail);
  };

  const clearUserDetail = () => {
    setUserDetail({
      id: null,
      email: "",
      firstName: "",
      lastName: "",
      role: "",
    });
  };

  return (
    <UserDetailContext.Provider
      value={{ userDetail, updateUserDetail, clearUserDetail }}
    >
      {children}
    </UserDetailContext.Provider>
  );
};
