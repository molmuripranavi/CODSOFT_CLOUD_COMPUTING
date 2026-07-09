import {
  FaCloud,
  FaGithub,
  FaUserCircle
} from "react-icons/fa";

function Navbar() {
  return (
    <nav className="navbar">

      <div className="logo">
        <FaCloud className="logo-icon" />
        <span>CloudVault</span>
      </div>

      <ul className="nav-links">
        <li><a href="#">Dashboard</a></li>
        <li><a href="#">My Files</a></li>
        <li><a href="#">About</a></li>
      </ul>

      <div className="nav-right">

        <a
          href="https://github.com/molmuripranavi"
          target="_blank"
          rel="noreferrer"
          className="github-btn"
        >
          <FaGithub />
        </a>

        <FaUserCircle className="profile-icon" />

      </div>

    </nav>
  );
}

export default Navbar;