import { motion } from "framer-motion";
import {
  FaCloudUploadAlt,
  FaGithub,
  FaShieldAlt,
  FaRocket,
  FaDatabase
} from "react-icons/fa";

function Hero() {
  return (
    <section className="hero">

      <motion.div
        className="hero-left"
        initial={{ opacity: 0, x: -70 }}
        animate={{ opacity: 1, x: 0 }}
        transition={{ duration: 0.8 }}
      >
        <span className="hero-tag">
          🚀 Cloud Storage Platform
        </span>

        <h1>
          Store.
          <br />
          Manage.
          <br />
          <span>Access Anywhere.</span>
        </h1>

        <p>
          CloudVault Pro is a modern cloud storage application
          built using Spring Boot and React. Upload, preview,
          download and organize your files securely through an
          elegant dashboard.
        </p>

        <div className="hero-buttons">

          <button className="primary-btn">
            <FaCloudUploadAlt />
            Upload Files
          </button>

          <button
            className="secondary-btn"
            onClick={() =>
              window.open(
                "https://github.com/yourusername",
                "_blank"
              )
            }
          >
            <FaGithub />
            GitHub
          </button>

        </div>

        <div className="hero-features">

          <div>
            <FaShieldAlt />
            Secure Storage
          </div>

          <div>
            <FaRocket />
            Lightning Fast
          </div>

          <div>
            <FaDatabase />
            Unlimited Files
          </div>

        </div>

      </motion.div>

      <motion.div
        className="hero-right"
        initial={{ opacity: 0, scale: .6 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 1 }}
      >

        <div className="hero-cloud">

          ☁️

        </div>

      </motion.div>

    </section>
  );
}

export default Hero;