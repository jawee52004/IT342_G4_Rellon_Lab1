import { useNavigate, Link } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();

  const logout = () => {
    navigate("/");
  };

  return (
    <div className="container">
      <h2>Dashboard</h2>

      <p>Welcome! You are logged in.</p>

      <Link to="/profile">Go to Profile</Link>
      <br /><br />

      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default Dashboard;
