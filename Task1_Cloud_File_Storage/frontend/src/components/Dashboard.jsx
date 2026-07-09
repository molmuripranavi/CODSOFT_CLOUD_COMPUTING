import { motion } from "framer-motion";
import {
  FaFolderOpen,
  FaCloud,
  FaUpload,
} from "react-icons/fa";

function Dashboard({ totalFiles = 0, storageInfo }) {
  return (
    <section className="dashboard-section">
      {/* Total Files */}
      <motion.div
        className="dashboard-card"
        whileHover={{
          y: -10,
          scale: 1.03,
        }}
      >
        <FaFolderOpen className="dashboard-icon" />

        <h3>Total Files</h3>

        <h1>{totalFiles}</h1>
      </motion.div>

      {/* Storage Used */}
      <motion.div
        className="dashboard-card"
        whileHover={{
          y: -10,
          scale: 1.03,
        }}
      >
        <FaCloud className="dashboard-icon" />

        <h3>Storage Used</h3>

        <h1>{storageInfo?.totalSize || "0 Bytes"}</h1>
      </motion.div>

      {/* Total Uploads */}
      <motion.div
        className="dashboard-card"
        whileHover={{
          y: -10,
          scale: 1.03,
        }}
      >
        <FaUpload className="dashboard-icon" />

        <h3>Total Uploads</h3>

        <h1>{storageInfo?.totalFiles || 0}</h1>
      </motion.div>
    </section>
  );
}

export default Dashboard;