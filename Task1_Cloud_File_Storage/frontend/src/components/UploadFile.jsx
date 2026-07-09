import { useState } from "react";
import API from "../services/api";
import { FaCloudUploadAlt } from "react-icons/fa";
import { toast } from "react-toastify";

function UploadFile({ refreshFiles }) {
  const [progress, setProgress] = useState(0);
  const [uploading, setUploading] = useState(false);
  const [files, setFiles] = useState([]);
  const [message, setMessage] = useState("");
  const [dragActive, setDragActive] = useState(false);

  // Select files
  const handleChange = (e) => {
    setFiles(Array.from(e.target.files));
  };

  // Drag Events
  const handleDragOver = (e) => {
    e.preventDefault();
    setDragActive(true);
  };

  const handleDragLeave = () => {
    setDragActive(false);
  };

  const handleDrop = (e) => {
    e.preventDefault();
    setDragActive(false);

    if (e.dataTransfer.files.length > 0) {
      setFiles(Array.from(e.dataTransfer.files));
    }
  };

  // Upload
  const handleUpload = async () => {
    if (files.length === 0) {
      toast.warning("Please select at least one file.");
      return;
    }

    const formData = new FormData();

    files.forEach((file) => {
      formData.append("file", file);
    });

    try {
      setUploading(true);
setProgress(0);

const response = await API.post("/upload", formData, {
  onUploadProgress: (progressEvent) => {
    const percentCompleted = Math.round(
      (progressEvent.loaded * 100) / progressEvent.total
    );

    setProgress(percentCompleted);
  },
});
      setMessage(response.data);

toast.success("Files uploaded successfully!");

setFiles([]);

setProgress(100);

refreshFiles();

setTimeout(() => {
  setUploading(false);
  setProgress(0);
  setMessage("");
}, 2000);

    } catch (error) {

  console.error(error);

  setUploading(false);

  setProgress(0);

  setMessage("Upload Failed.");

toast.error("Upload failed!");

}
  };

  return (
    <div className="upload-container">

      <h2>Upload Files</h2>

      <p>Upload one or multiple files securely.</p>

      <div
        className={`drop-zone ${dragActive ? "active" : ""}`}
        onDragOver={handleDragOver}
        onDragLeave={handleDragLeave}
        onDrop={handleDrop}
      >
        <FaCloudUploadAlt className="upload-icon" />

        <h3>Drag & Drop Files Here</h3>

        <p>or</p>

        <label className="browse-btn">
          Browse Files
          <input
            type="file"
            multiple
            hidden
            onChange={handleChange}
          />
        </label>

      </div>

      {files.length > 0 && (
        <div className="selected-files">

          <h4>Selected Files</h4>

          {files.map((file, index) => (
            <p key={index}>📄 {file.name}</p>
          ))}

        </div>
      )}
       
       {uploading && (
  <div className="progress-container">

    <div
      className="progress-bar"
      style={{ width: `${progress}%` }}
    >
      {progress}%
    </div>

  </div>
)}
      <button
        className="upload-btn"
        onClick={handleUpload}
      >
        Upload Files
      </button>

      <br />
      <br />

      {message && <p>{message}</p>}

    </div>
  );
}

export default UploadFile;