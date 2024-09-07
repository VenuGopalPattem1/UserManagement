import React from 'react';
import { Link } from 'react-router-dom';
import './Style.css'; // Import the CSS file

function Navbar() {
  return (
    <nav className="navbar  navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid body">
        <Link className="navbar-brand" to="/">
          User Management
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/">
                Register
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/activate">
                Activate
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/login">
                Login
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/recover">
                Recover
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/all">
                All Users
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
