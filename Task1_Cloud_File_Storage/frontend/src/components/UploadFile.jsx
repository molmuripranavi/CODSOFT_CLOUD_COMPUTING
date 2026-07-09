import { useState } from "react";
import API from "../services/api";

function UploadFile({refreshFiles }) {
  const [files, setFiles] = useState([]);
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFiles(e.target.files);
  };

  const handleUpload = async () => {
    if (files.length === 0) {
      alert("Please select at least one file.");
      return;
    }

    const formData = new FormData();

    for (let i = 0; i < files.length; i++) {
      formData.append("file", files[i]);
    }

    try {
      const response = await API.post("/upload", formData);

      setMessage(response.data);
      refreshFiles();

      alert("Files uploaded successfully!");

      setFiles([]);

      onUpload();

    } catch (error) {
      console.error(error);

      if (error.response) {
        setMessage(error.response.data);
      } else {
        setMessage("Upload failed.");
      }

      alert("Upload failed!");
    }
  };

  return (
    <div className="upload-container">
      <h2>Upload Files</h2>

      <input
        type="file"
        multiple
        onChange={handleChange}
      />

      <br />
      <br />

      <button onClick={handleUpload}>
        Upload
      </button>

      <br />
      <br />

      <p>{files.length} file(s) selected</p>

      {message && (
        <>
          <h3>Response</h3>
          <p>{message}</p>
        </>
      )}
    </div>
  );
}

export default UploadFile;