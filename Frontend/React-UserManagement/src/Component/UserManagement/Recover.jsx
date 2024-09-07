import axios from 'axios';
import { useFormik } from 'formik';
import React, { useState } from 'react';
import './Style.css'; // Import the CSS file
import { useNavigate } from 'react-router-dom';

function Recover() {
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      name:'',
      email: "",
    },
    onSubmit: (data) => {
      axios({
        method: 'post',
        url: 'http://localhost:8080/app/recover',
        data: data
      });
      alert("Your password sent to your account");
      navigate("/")
    }
  });
  return (
    <div className="register-container">
    <div className="register-box">
      <h3>Welcome to User Login Page</h3>
      <div className="form-group">
          <label>User Name</label>
          <input type="text" name="name" value={formik.values.name} onChange={formik.handleChange} placeholder="Enter your Name" />
        </div>
      <form onSubmit={formik.handleSubmit}>
        <div className="form-group">
          <label>User Email</label>
          <input type="email" name="email" value={formik.values.email} onChange={formik.handleChange} placeholder="Enter your email" />
        </div>
        <button className="submit-button" type="submit">Submit</button>
      </form>
    </div>
  </div>
  )
}

export default Recover