import React, { useState } from "react";
import axios from "axios";

import BASE_URL from '../api';
function UserPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault(); // Prevent default form submission

    try {
      const {data}  = await axios.post(
        "http://localhost:9930/api/auth/signin",
        {
          email,
          password,
        }
      );
      console.log("response: ", data)
      const{token,refreshToken}=data
      console.log("token: " ,token)

      localStorage.setItem("token", token);
    } catch (error) {
      setErrorMessage(
        "Login failed. Please check your credentials or network connection."
      );
    }
  };

  return (
    <div className="login-form">
      <h1>User Login</h1>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <form onSubmit={handleSubmit}>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default UserPage;
