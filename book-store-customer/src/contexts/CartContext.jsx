import React, { createContext, useState } from "react";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState(
    localStorage.getItem("cartItems") == null
      ? []
      : JSON.parse(localStorage.getItem("cartItems"))
  );

  const addToCart = (bookId) => {
    setCartItems((prevCartItems) => {
      console.log(prevCartItems);
      if (!prevCartItems.includes(bookId)) {
        const updatedCartItems = [...prevCartItems, bookId];
        localStorage.setItem("cartItems", JSON.stringify(updatedCartItems));
        return updatedCartItems;
      }
      return prevCartItems;
    });
  };

  const removeFromCart = (bookId) => {
    setCartItems((prevCartItems) =>
      prevCartItems.filter((id) => id !== bookId)
    );
  };

  const clearCart = () => {
    localStorage.removeItem("cartItems");
    setCartItems([]);
  };

  return (
    <CartContext.Provider
      value={{
        cartItems,
        addToCart,
        removeFromCart,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};
