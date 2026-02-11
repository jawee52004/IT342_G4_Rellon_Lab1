'use client';

import { useNavigate, Link, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import "../components/Profile.css";

function Profile() {
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
    navigate("/login", { replace: true });
  };

  if (!user) return null;

  return (
    <div className="profile-root">
      {/* Sidebar */}
      <div className="profile-sidebar">
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
      <div className="profile-main">
        <div className="profile-content">
          <h1 className="profile-header">Profile</h1>

          <div className="profile-info">
            <p><strong>Full Name:</strong> {user.fullName}</p>
            <p><strong>Email:</strong> {user.email}</p>
            <p>
              <strong>Account Created:</strong>{" "}
              {user.createdAt
                ? new Date(user.createdAt).toLocaleString()
                : "N/A"}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Profile;
