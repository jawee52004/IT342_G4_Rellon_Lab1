import { Link } from "react-router-dom";

function Profile() {
  return (
    <div className="container">
      <h2>Profile</h2>

      <p>User profile info goes here.</p>

      <Link to="/dashboard">Back to Dashboard</Link>
    </div>
  );
}

export default Profile;
