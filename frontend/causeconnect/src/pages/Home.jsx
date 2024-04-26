import React from 'react'
import { Link } from 'react-router-dom'

const Home = () => {
  return (
    <div>
      <h1>Welcome!</h1>
      <p>Choose your role:</p>
      <ul>
        <li>
          <Link to="/ngo">NGO</Link>
        </li>
        <li>
          <Link to="/user">User</Link>
        </li>
      </ul>
    </div>
  );
}

export default Home
