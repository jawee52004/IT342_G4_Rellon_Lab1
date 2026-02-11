import React, { useState } from "react";
import { Link } from "react-router-dom";
import api from "../api/axios"; // Axios instance
import "../components/Register.css";

export default function Register() {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState(""); // added email
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleRegister = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      await api.post("/api/auth/register", {
        fullName,
        email,           // send email to backend
        passwordHash: password,
      });
      setMessage("Registration successful!");
      setFullName("");
      setEmail("");      // clear email field
      setPassword("");
    } catch (error) {
      console.error(error);
      setMessage("Registration failed");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="register-page">
      <div className="register-card">
        {/* Header */}
        <div className="register-header">
          <h2 className="register-title">Create Account</h2>
          <p className="register-subtitle">Enter your details to register</p>
        </div>

        {/* Message */}
        {message && <div className="register-message">{message}</div>}

        {/* Form */}
        <form onSubmit={handleRegister}>
          <div className="register-form-group">
            <label htmlFor="fullName" className="register-label">
              Full Name
            </label>
            <input
              id="fullName"
              type="text"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
              required
              className="register-input"
            />
          </div>

          <div className="register-form-group">
            <label htmlFor="email" className="register-label">
              Email
            </label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="register-input"
            />
          </div>

          <div className="register-form-group">
            <label htmlFor="password" className="register-label">
              Password
            </label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="register-input"
            />
          </div>

          <button
            type="submit"
            disabled={isLoading}
            className="register-button"
          >
            {isLoading ? "Registering..." : "Register"}
          </button>
        </form>

        {/* Footer */}
        <div className="register-footer">
          Already have an account? <Link to="/">Login here</Link>
        </div>
      </div>
    </div>
  );
}
