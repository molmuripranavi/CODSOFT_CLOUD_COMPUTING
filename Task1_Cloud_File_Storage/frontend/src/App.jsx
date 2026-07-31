import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import Dashboard from "./components/Dashboard";
import UploadFile from "./components/UploadFile";
import SearchBar from "./components/SearchBar";
import FileList from "./components/FileList";
import { ToastContainer } from "react-toastify";
import { useState} from "react";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";
import Footer from "./components/Footer";


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
  <div>

    <Navbar />

    <Hero />

    <div className="container">
      

      <Dashboard
        totalFiles={totalFiles}
        storageInfo={storageInfo}
      />

      <UploadFile
        refreshFiles={refreshFiles}
      />

      <SearchBar
        search={search}
        setSearch={setSearch}
        filter={filter}
        setFilter={setFilter}
      />

      <FileList
        refresh={refresh}
        search={search}
        filter={filter}
        setTotalFiles={setTotalFiles}
        setStorageInfo={setStorageInfo}
      />

    </div>
     <Footer />
    <ToastContainer
      position="top-right"
      autoClose={2500}
      newestOnTop
      closeOnClick
      pauseOnHover
    />

  </div>
);
}

export default App;