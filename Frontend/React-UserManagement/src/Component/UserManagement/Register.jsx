import axios from 'axios';
import { useFormik } from 'formik';
import { useNavigate } from 'react-router-dom';
import React, { useState } from 'react';
import './Style.css'; // Import the CSS file

function Register() {
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      name: "",
      email: "",
      mobileNo: "",
      aadharNo: "",
      gender: ""
    },
    onSubmit: (data) => {
      axios({
        method: 'post',
        url: 'http://localhost:8080/app/save',
        data: data
      });
      alert("Confirmation Message Sent To Your E-Mail");
      navigate("/")
    }
  });

  return (
    <div className="register-container">
      <div className="register-box">
        <h3>Welcome to User Registration</h3>
        <form onSubmit={formik.handleSubmit}>
          <div className="form-group">
            <label>User Name</label>
            <input type="text" name="name" value={formik.values.name} onChange={formik.handleChange} placeholder="Enter your name" />
          </div>
          <div className="form-group">
            <label>User Email</label>
            <input type="email" name="email" value={formik.values.email} onChange={formik.handleChange} placeholder="Enter your email" />
          </div>
          <div className="form-group">
            <label>User Mobile Number</label>
            <input type="number" name="mobileNo" value={formik.values.mobileNo} onChange={formik.handleChange} placeholder="Enter your mobile number" />
          </div>
          <div className="form-group">
            <label>User Aadhar Number</label>
            <input type="number" name="aadharNo" value={formik.values.aadharNo} onChange={formik.handleChange} placeholder="Enter your Aadhar number" />
          </div>
          <div className="form-group">
            <label>Select Your Gender</label>
            <div>
              <input type="radio" value="Male" name="gender" onChange={formik.handleChange} /> Male
              <input type="radio" value="Female" name="gender" onChange={formik.handleChange} /> Female
            </div>
          </div>
          <button className="submit-button" type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
}

export default Register;
