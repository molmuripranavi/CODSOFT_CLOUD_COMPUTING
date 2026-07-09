import { motion } from "framer-motion";
import {
  FaCloudUploadAlt,
  FaLock,
  FaBolt
} from "react-icons/fa";

function Hero() {
  return (
    <section className="hero">

      <motion.div
        className="hero-left"
        initial={{ x: -80, opacity: 0 }}
        animate={{ x: 0, opacity: 1 }}
        transition={{ duration: .8 }}
      >

        <h1>
          Secure Cloud
          <span> File Storage</span>
        </h1>

        <p>
          Store, organize, upload and manage your files
          securely from anywhere with a fast and modern
          cloud storage platform.
        </p>

        <div className="hero-buttons">

          <button className="primary-btn">
            <FaCloudUploadAlt />
            Upload Files
          </button>

          <button className="secondary-btn">
            Learn More
          </button>

        </div>

        <div className="hero-features">

          <div>
            <FaLock />
            Secure
          </div>

          <div>
            <FaBolt />
            Fast
          </div>

          <div>
            ☁ Reliable
          </div>

        </div>

      </motion.div>

      <motion.div
        className="hero-right"
        initial={{ x: 80, opacity: 0 }}
        animate={{ x: 0, opacity: 1 }}
        transition={{ duration: .8 }}
      >

        <div className="floating-cloud">

            ☁

        </div>

      </motion.div>

    </section>
  );
}

export default Hero;