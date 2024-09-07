import axios from 'axios';
import { useFormik } from 'formik';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Style.css'; // Import the CSS file

function Login() {
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      email: "",
      password: ""
    },
    onSubmit: (data) => {
      axios({
        method: 'post',
        url: 'http://localhost:8080/app/login',
        data: data
      });
      alert("Your sucessfully logged into your account");
      navigate("/")
    }
  });
  return (
    <div className="register-container">
      <div className="register-box">
        <h3>Welcome to User Login Page</h3>
        <form onSubmit={formik.handleSubmit}>
          <div className="form-group">
            <label>User Email</label>
            <input type="email" name="email" value={formik.values.email} onChange={formik.handleChange} placeholder="Enter your email" />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input type="text" name="password" value={formik.values.password} onChange={formik.handleChange} placeholder="confirm your password" />
          </div>
          <button className="submit-button" type="submit">Submit</button>
        </form>
      </div>
    </div>
  )
}

export default Login