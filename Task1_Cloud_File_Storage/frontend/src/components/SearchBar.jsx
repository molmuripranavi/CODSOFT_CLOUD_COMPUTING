import { FaSearch, FaFilter } from "react-icons/fa";

function SearchBar({
  search,
  setSearch,
  filter,
  setFilter,
}) {
  return (
    <div className="search-panel">

      <div className="search-box">

        <FaSearch className="search-icon" />

        <input
          type="text"
          placeholder="Search files..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

      </div>

      <div className="filter-box">

        <FaFilter />

        <select
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
        >
          <option value="All">All Files</option>
          <option value="PDF">PDF</option>
          <option value="IMAGE">Images</option>
          <option value="DOC">Documents</option>
          <option value="VIDEO">Videos</option>
          <option value="ARCHIVE">Archives</option>
        </select>

      </div>

    </div>
  );
}

export default SearchBar;