import { useState } from "react";
import Navbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";
import UploadFile from "./components/UploadFile";
import SearchBar from "./components/SearchBar";
import FileList from "./components/FileList";
import "./App.css";

function App() {
  const [refresh, setRefresh] = useState(false);
  const [search, setSearch] = useState("");
  const [totalFiles, setTotalFiles] = useState(0);

  const refreshFiles = () => {
    setRefresh((prev) => !prev);
  };

  return (
    <>
      <Navbar />

      <div className="container">
        <Dashboard totalFiles={totalFiles} />

        <UploadFile refreshFiles={refreshFiles} />

        <SearchBar
          search={search}
          setSearch={setSearch}
        />

        <FileList
          refresh={refresh}
          search={search}
          setTotalFiles={setTotalFiles}
        />
      </div>
    </>
  );
}

export default App;