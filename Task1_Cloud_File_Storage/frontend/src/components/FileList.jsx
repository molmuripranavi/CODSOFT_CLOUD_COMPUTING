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
const BASE_URL = import.meta.env.VITE_API_URL;

function FileList({
  refresh,
  search,
  filter,
  setTotalFiles,
  setStorageInfo,
}) {
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(true);

  // Fetch Files
  const fetchFiles = async () => {
  try {
    setLoading(true);

    const response = await API.get("");

    setFiles(response.data);
    setTotalFiles(response.data.length);

    const storageResponse = await API.get("/storage");
    setStorageInfo(storageResponse.data);

  } catch (error) {
    console.error(error);
  } finally {
    setLoading(false);
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
  // Download
const handleDownload = (filename) => {
  window.open(
    `${BASE_URL}/download/${encodeURIComponent(filename)}`,
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
  if (loading) {
  return (
    <div className="loading">
      <div className="loader"></div>
      <h3>Loading Files...</h3>
    </div>
  );
}

 return (
  <div className="file-list">

    <h2>My Files</h2>

    <p>
      Securely manage all uploaded files.
    </p>

    {filteredFiles.length === 0 ? (

      <div className="empty-state">

        <h3>📂 No Files Found</h3>

        <p>
          Upload your first file to get started.
        </p>

      </div>

    ) : (

      <div className="file-grid">

        {filteredFiles.map((file,index)=>(

          <div
            className="file-card"
            key={index}
          >

            <div className="file-header">

              <div className="file-icon">

                {getFileIcon(file.name)}

              </div>

              <div className="file-info">
  <h3 title={file.name}>
    {file.name}
  </h3>

  <p>{file.type}</p>
</div>

            </div>

            <div className="file-size">

              📦 {file.size}

            </div>

            <div className="file-date">

              📅 {file.uploadedDate}

            </div>

            <div className="file-actions">

              <button
                className="download-btn"
                onClick={()=>
                  handleDownload(file.name)
                }
              >
                <FaDownload/>
              </button>

              <button
  className="view-btn"
  onClick={() =>
    window.open(
      `${BASE_URL}/view/${encodeURIComponent(file.name)}`,
      "_blank"
    )
  }
>
  View
</button>

              <button
                className="delete-btn"
                onClick={()=>
                  handleDelete(file.name)
                }
              >
                <FaTrash/>
              </button>

            </div>

          </div>

        ))}

      </div>

    )}

  </div>
);
}

export default FileList;