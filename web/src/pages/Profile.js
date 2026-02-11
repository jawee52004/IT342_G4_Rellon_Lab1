'use client';

import { useNavigate, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import "../components/Profile.css";

function Profile() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);

  // Protect page (redirect if not logged in)
  useEffect(() => {
    try {
      const storedUser = localStorage.getItem("user");

      if (!storedUser) {
        navigate("/", { replace: true });
        return;
      }

      const parsedUser = JSON.parse(storedUser);

      // Extra safety check
      if (!parsedUser?.id) {
        localStorage.removeItem("user");
        navigate("/", { replace: true });
        return;
      }

      setUser(parsedUser);
    } catch (error) {
      console.error("Invalid user data:", error);
      localStorage.removeItem("user");
      navigate("/", { replace: true });
    }
  }, [navigate]);

  const logout = () => {
    localStorage.removeItem("user");
    navigate("/", { replace: true }); // prevents back navigation
  };

  if (!user) return null; // Prevent rendering before validation completes

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
            <p>
              <strong>Full Name:</strong> {user.fullName || "N/A"}
            </p>

            <p>
              <strong>Email:</strong> {user.email || "N/A"}
            </p>

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
