import React, { createContext, useState } from "react";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState(
    localStorage.getItem("cartItems") == null
      ? []
      : JSON.parse(localStorage.getItem("cartItems"))
  );

  const addToCart = (bookId) => {
    setCartItems((prevCartItems) => [...prevCartItems, bookId]);
    localStorage.setItem("cartItems", JSON.stringify(cartItems));
  };

  const removeFromCart = (bookId) => {
    setCartItems((prevCartItems) =>
      prevCartItems.filter((id) => id !== bookId)
    );
  };

  const clearCart = () => {
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
