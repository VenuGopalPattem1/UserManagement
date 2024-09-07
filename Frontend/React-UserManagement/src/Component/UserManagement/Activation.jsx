import axios from 'axios';
import { useFormik } from 'formik';
import { useNavigate } from 'react-router-dom';
import React, { useState } from 'react';
import './Style.css'; // Import the CSS file

function Activation() {
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      email: "",
      tempPass: "",
      newPass: "",
      confirmPass: ""
    },
    onSubmit: (data) => {
      axios({
        method: 'post',
        url: 'http://localhost:8080/app/activate',
        data: data
      });
      alert("Your Account has Been Activated");
      navigate("/")
    }
  });

  return (
    <div className="register-container">
    <div className="register-box">
      <h3>Welcome to User Actvation</h3>
      <form onSubmit={formik.handleSubmit}>
        <div className="form-group">
          <label>User Email</label>
          <input type="email" name="email" value={formik.values.email} onChange={formik.handleChange} placeholder="Enter your email" />
        </div>
        <div className="form-group">
          <label>User Temporary Password</label>
          <input type="text" name="tempPass" value={formik.values.tempPass} onChange={formik.handleChange} placeholder="Enter your temp pass" />
        </div>
        <div className="form-group">
          <label>User New Password</label>
          <input type="text" name="newPass" value={formik.values.newPass} onChange={formik.handleChange} placeholder="Enter your mnew password" />
        </div>
        <div className="form-group">
          <label>User Confirm Password</label>
          <input type="text" name="confirmPass" value={formik.values.confirmPass} onChange={formik.handleChange} placeholder="confirm your new password" />
        </div>
        <button className="submit-button" type="submit">Submit</button>
      </form>
    </div>
  </div>
  )
}

export default Activation