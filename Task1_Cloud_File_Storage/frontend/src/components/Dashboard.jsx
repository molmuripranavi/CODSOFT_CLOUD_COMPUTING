import { FaFolderOpen } from "react-icons/fa";

function Dashboard({ totalFiles }) {
  return (
    <div className="dashboard">
      <div className="dashboard-card">
        <FaFolderOpen className="dashboard-icon" />

        <div>
          <h3>Total Files</h3>
          <h1>{totalFiles}</h1>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;