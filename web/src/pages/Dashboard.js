import { useNavigate, Link } from "react-router-dom";
import { useEffect } from "react";
import "../components/Dashboard.css";

function Dashboard() {
  const navigate = useNavigate();

  useEffect(() => {
    const user = localStorage.getItem("user");

    if (!user) {
      navigate("/", { replace: true }); // replace prevents back navigation
    }
  }, [navigate]);

  const logout = () => {
    localStorage.removeItem("user");   // clear login
    navigate("/", { replace: true });  // replace history
  };

  return (
    <div className="dashboard-root">
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

      <div className="dashboard-main">
        <div className="dashboard-content">
          <h1 className="dashboard-header">Dashboard</h1>
          <p className="dashboard-text">Welcome! You are logged in.</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
