import { useEffect, useState } from "react";
import API from "../services/api";
import { toast } from "react-toastify";
import {
  FaFileAlt,
  FaFilePdf,
  FaFileWord,
  FaFileExcel,
  FaFilePowerpoint,
  FaFileImage,
  FaFileArchive,
  FaFileVideo,
  FaFileAudio,
  FaDownload,
  FaTrash,
} from "react-icons/fa";

function FileList({
  refresh,
  search,
  filter,
  setTotalFiles,
  setStorageInfo,
}) {
  const [files, setFiles] = useState([]);

  // Fetch Files
  const fetchFiles = async () => {
    try {
      const response = await API.get("");

      setFiles(response.data);
      setTotalFiles(response.data.length);

      const storageResponse = await API.get("/storage");
      setStorageInfo(storageResponse.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchFiles();
  }, [refresh]);

  // Search + Filter
  const filteredFiles = files.filter((file) => {
    const matchesSearch = file.name
      .toLowerCase()
      .includes(search.toLowerCase());

    switch (filter) {
      case "PDF":
        return matchesSearch && file.type === "PDF";

      case "IMAGE":
        return (
          matchesSearch &&
          ["PNG", "JPG", "JPEG", "GIF", "WEBP"].includes(file.type)
        );

      case "DOC":
        return (
          matchesSearch &&
          ["DOC", "DOCX", "TXT"].includes(file.type)
        );

      case "VIDEO":
        return (
          matchesSearch &&
          ["MP4", "AVI", "MOV", "MKV"].includes(file.type)
        );

      case "ARCHIVE":
        return (
          matchesSearch &&
          ["ZIP", "RAR", "7Z"].includes(file.type)
        );

      default:
        return matchesSearch;
    }
  });

  // Download
  const handleDownload = (filename) => {
    window.open(
      `http://localhost:8080/api/files/download/${filename}`,
      "_blank"
    );
  };

  // Delete
  const handleDelete = async (filename) => {
    const confirmDelete = window.confirm(
      `Delete "${filename}"?`
    );

    if (!confirmDelete) return;

    try {
      await API.delete(`/${filename}`);

      toast.success("File deleted successfully!");

      fetchFiles();
    } catch (error) {
      console.error(error);
      toast.error("Delete failed!");
    }
  };

  // File Icons
  const getFileIcon = (filename) => {
    const extension = filename
      .split(".")
      .pop()
      .toLowerCase();

    switch (extension) {
      case "pdf":
        return <FaFilePdf color="#dc2626" size={24} />;

      case "doc":
      case "docx":
        return <FaFileWord color="#2563eb" size={24} />;

      case "xls":
      case "xlsx":
        return <FaFileExcel color="#16a34a" size={24} />;

      case "ppt":
      case "pptx":
        return <FaFilePowerpoint color="#ea580c" size={24} />;

      case "jpg":
      case "jpeg":
      case "png":
      case "gif":
      case "webp":
        return <FaFileImage color="#9333ea" size={24} />;

      case "mp4":
      case "avi":
      case "mov":
      case "mkv":
        return <FaFileVideo color="#ef4444" size={24} />;

      case "mp3":
      case "wav":
        return <FaFileAudio color="#06b6d4" size={24} />;

      case "zip":
      case "rar":
      case "7z":
        return <FaFileArchive color="#ca8a04" size={24} />;

      default:
        return <FaFileAlt color="#6b7280" size={24} />;
    }
  };

  return (
    <div className="file-list">
      <h2>Uploaded Files</h2>
      <p>Manage all your uploaded files securely.</p>

      {filteredFiles.length === 0 ? (
        <p>No files found.</p>
      ) : (
        <table className="file-table">
          <thead>
            <tr>
              <th>File</th>
              <th>Type</th>
              <th>Size</th>
              <th>Date</th>
              <th>Download</th>
              <th>Delete</th>
            </tr>
          </thead>

          <tbody>
            {filteredFiles.map((file, index) => (
              <tr key={index}>
                <td>
                  <div
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: "12px",
                    }}
                  >
                    {getFileIcon(file.name)}
                    {file.name}
                  </div>
                </td>

                <td>{file.type}</td>

                <td>{file.size}</td>

                <td>{file.uploadedDate}</td>

                <td>
                  <button
                    className="download-btn"
                    onClick={() =>
                      handleDownload(file.name)
                    }
                  >
                    <FaDownload /> Download
                  </button>
                </td>

                <td>
                  <button
                    className="delete-btn"
                    onClick={() =>
                      handleDelete(file.name)
                    }
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