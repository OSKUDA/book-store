import { Link } from "react-router-dom";
import NavBar from "../NavBar";
import OrderGrid from "./OrderGrid";

const OrderPage = () => {
  const token = JSON.parse(localStorage.getItem("token"));
  if (token === null) {
    return (
      <div className="form-container vertical-center">
        <form>
          <h1>You are not authenticated. Token=&quot;&quot;</h1>
          <p>
            already have an account? <Link to={"/login"}>sign-in here</Link>
          </p>
          <p>
            don&apos;t have an account?{" "}
            <Link to={"/register"}>register here</Link>
          </p>
        </form>
      </div>
    );
  } else {
    return (
      <div>
        <NavBar />
        <h1 className="title-center">Orders</h1>
        <OrderGrid minOrderList={result.minOrderList} />
      </div>
    );
  }
};

const result = {
  status: true,
  message: "success",
  minOrderList: [
    {
      id: 1,
      minUser: {
        id: 1,
        email: "hi@gmail.com",
        firstName: "hi",
        lastName: "Shrestha",
        role: "USER",
      },
      minBookList: [
        {
          id: 7,
          title: "Crash",
          author: "J. G. Ballard",
          publicationDate: 1973,
          quantity: 11,
          price: 1073,
        },
        {
          id: 9,
          title: "Candide, ou l'Optimisme",
          author: "Voltaire",
          publicationDate: 1759,
          quantity: 12,
          price: 1136,
        },
        {
          id: 1400,
          title: "The Gemini Contenders",
          author: "Robert Ludlum",
          publicationDate: 1976,
          quantity: 9,
          price: 1384,
        },
      ],
    },
    {
      id: 2,
      minUser: {
        id: 1,
        email: "hi@gmail.com",
        firstName: "hi",
        lastName: "Shrestha",
        role: "USER",
      },
      minBookList: [
        {
          id: 2,
          title: "The Plague",
          author: "Albert Camus",
          publicationDate: 1947,
          quantity: 12,
          price: 2495,
        },
      ],
    },
    {
      id: 3,
      minUser: {
        id: 1,
        email: "hi@gmail.com",
        firstName: "hi",
        lastName: "Shrestha",
        role: "USER",
      },
      minBookList: [
        {
          id: 20,
          title: "God Emperor of Dune",
          author: "Frank Herbert",
          publicationDate: 1981,
          quantity: 12,
          price: 2017,
        },
        {
          id: 10,
          title: "Chapterhouse Dune",
          author: "Frank Herbert",
          publicationDate: 1985,
          quantity: 5,
          price: 2070,
        },
        {
          id: 4,
          title: "A Wizard of Earthsea",
          author: "Ursula K. Le Guin",
          publicationDate: 1968,
          quantity: 5,
          price: 2000,
        },
      ],
    },
    {
      id: 4,
      minUser: {
        id: 4,
        email: "alex@gmail.com",
        firstName: "Alex",
        lastName: "Nepali",
        role: "USER",
      },
      minBookList: [
        {
          id: 26,
          title: "Johnny Got His Gun",
          author: "Dalton Trumbo",
          publicationDate: 1939,
          quantity: 12,
          price: 1942,
        },
      ],
    },
  ],
  page: 0,
  size: 4,
};
export default OrderPage;
