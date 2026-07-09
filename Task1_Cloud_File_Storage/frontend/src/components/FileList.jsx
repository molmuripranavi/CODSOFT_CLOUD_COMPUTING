import { useEffect, useState } from "react";
import API from "../services/api";
import {
  FaFileAlt,
  FaDownload,
  FaTrash
} from "react-icons/fa";

function FileList({ refresh, search, setTotalFiles }) {
  const [files, setFiles] = useState([]);

  // Fetch Files
  const fetchFiles = async () => {
    try {
      const response = await API.get("");

      setFiles(response.data);
      setTotalFiles(response.data.length);

    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchFiles();
  }, [refresh]);

  // Search Filter
  const filteredFiles = files.filter((file) =>
    file.toLowerCase().includes(search.toLowerCase())
  );

  // Download File
  const handleDownload = (filename) => {
    window.open(
      `http://localhost:8080/api/files/download/${filename}`,
      "_blank"
    );
  };

  // Delete File
  const handleDelete = async (filename) => {
    const confirmDelete = window.confirm(
      `Delete "${filename}"?`
    );

    if (!confirmDelete) return;

    try {
      await API.delete(`/${filename}`);

      alert("File deleted successfully.");

      fetchFiles();

    } catch (error) {

      console.error(error);

      alert("Delete failed.");
    }
  };

  return (
    <div className="file-list">

      <h2>
        <FaFileAlt style={{ marginRight: "10px" }} />
        Uploaded Files
      </h2>

      <p>
        View, download and manage your uploaded files.
      </p>

      {filteredFiles.length === 0 ? (

        <p>No files available.</p>

      ) : (

        <table className="file-table">

          <thead>

            <tr>
              <th>File Name</th>
              <th>Download</th>
              <th>Delete</th>
            </tr>

          </thead>

          <tbody>

            {filteredFiles.map((file, index) => (

              <tr key={index}>

                <td>
                  <FaFileAlt
                    style={{
                      color: "#2563eb",
                      marginRight: "10px"
                    }}
                  />
                  {file}
                </td>

                <td>
                  <button
                    className="download-btn"
                    onClick={() => handleDownload(file)}
                  >
                    <FaDownload /> Download
                  </button>
                </td>

                <td>
                  <button
                    className="delete-btn"
                    onClick={() => handleDelete(file)}
                  >
                    <FaTrash /> Delete
                  </button>
                </td>

              </tr>

            ))}

          </tbody>

        </table>

      )}

    </div>
  );
}

export default FileList;