import React from "react";
import { Link } from "react-router-dom";
const Homepage = () => {
  return (
    <div className="homepage">
      <div className="hero-section">
        <h1>Welcome to BookStore.com</h1>
        <p className="hero-description">
          —Your Virtual Bookstore for all your reading needs—
        </p>
        <Link to="/dashboard/books" className="browse-link">
          Browse Books
        </Link>
      </div>
      <div className="features-section">
        <div className="feature">
          <i className="fas fa-book"></i>
          <h3>Wide Selection of Books</h3>
          <p>
            Discover a vast collection of books in various genres. From
            bestsellers to classics, we have something for everyone.
          </p>
        </div>
        <div className="feature">
          <i className="fas fa-truck"></i>
          <h3>Fast Delivery</h3>
          <p>
            Order your favorite books and enjoy fast and reliable delivery to
            your doorstep. We make sure you receive your books promptly.
          </p>
        </div>
        <div className="feature">
          <i className="fas fa-heart"></i>
          <h3>Personalized Recommendations</h3>
          <p>
            Get personalized book recommendations based on your preferences and
            reading history. Discover new books tailored just for you.
          </p>
        </div>
      </div>
      <div className="call-to-action">
        <h2>Ready to embark on a reading journey?</h2>
        <Link to="/dashboard/cart" className="order-link">
          Order Now
        </Link>
      </div>
    </div>
  );
};

export default Homepage;
