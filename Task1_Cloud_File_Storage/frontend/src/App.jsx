import { useState } from "react";
import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import Dashboard from "./components/Dashboard";
import UploadFile from "./components/UploadFile";
import SearchBar from "./components/SearchBar";
import FileList from "./components/FileList";
import { ToastContainer } from "react-toastify";

import "react-toastify/dist/ReactToastify.css";
import "./App.css";

function App() {
  // Refresh uploaded files
  const [refresh, setRefresh] = useState(false);

  // Search text
  const [search, setSearch] = useState("");
  const [filter, setFilter] = useState("All");

  // Dashboard total files
  const [totalFiles, setTotalFiles] = useState(0);

  // Storage Information
  const [storageInfo, setStorageInfo] = useState({
    totalFiles: 0,
    totalSize: "0 Bytes",
    totalBytes: 0,
  });

  // Refresh callback
  const refreshFiles = () => {
    setRefresh((prev) => !prev);
  };

  return (
    <>
      <Navbar />

      <Hero />

      <div className="container">

        {/* Dashboard */}
        <Dashboard
          totalFiles={totalFiles}
          storageInfo={storageInfo}
        />

        {/* Upload */}
        <UploadFile
          refreshFiles={refreshFiles}
        />

        {/* Search */}
        <SearchBar
  search={search}
  setSearch={setSearch}
  filter={filter}
  setFilter={setFilter}
/>

        {/* File List */}
        <FileList
  refresh={refresh}
  search={search}
  filter={filter}
  setTotalFiles={setTotalFiles}
  setStorageInfo={setStorageInfo}
/>

      </div>

      <ToastContainer
        position="top-right"
        autoClose={2500}
        newestOnTop
        closeOnClick
        pauseOnHover
      />
    </>
  );
}

export default App;