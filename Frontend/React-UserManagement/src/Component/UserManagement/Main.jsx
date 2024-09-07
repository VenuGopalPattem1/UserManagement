import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Register from "./Register"
import Activation from "./Activation"
import Login from "./Login"
import Recover from "./Recover"
import AllUsers from "./AllUsers"
import Navbar from './Navbar'
import './Style.css'; // Import the CSS file

function Main() {
    return (
        <div className=' container-fluid'>
            
            <BrowserRouter>
            <Navbar />
                <Routes>
                    <Route path='/' element={<Register/>}></Route>
                    <Route path='activate' element={<Activation/>}></Route>
                    <Route path='login' element={<Login/>}></Route>
                    <Route path='recover' element={<Recover/>}></Route>
                    <Route path='all' element={<AllUsers/>}></Route>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default Main