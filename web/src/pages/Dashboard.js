'use client';

import { useNavigate, Link, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import "../components/Dashboard.css";

function Dashboard() {
  const navigate = useNavigate();
  const location = useLocation();
  const [user, setUser] = useState(null);

  // Protect page
  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (!storedUser) {
      navigate("/login", { state: { from: location }, replace: true });
    } else {
      setUser(JSON.parse(storedUser));
    }
  }, [navigate, location]);

  const logout = () => {
    localStorage.removeItem("user");
    navigate("/login", { replace: true }); // prevents back button to dashboard
  };

  if (!user) return null; // prevent rendering before user is loaded

  return (
    <div className="dashboard-root">
      {/* Sidebar */}
      <div className="dashboard-sidebar">
        <h1>Menu</h1>
        <nav>
          <Link to="/dashboard">Dashboard</Link>
          <Link to="/profile">Profile</Link>
        </nav>

        <button className="logout-button" onClick={logout}>
          Logout
        </button>
      </div>

      {/* Main Content */}
      <div className="dashboard-main">
        <div className="dashboard-content">
          <h1 className="dashboard-header">Dashboard</h1>
          <p className="dashboard-text">
            Welcome, {user.fullName}! You are logged in.
          </p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
