import React from 'react'
import {BrowserRouter as Router, Navigate, Route, Routes} from 'react-router-dom'
import {AuthProvider} from './components/auth/AuthContext'
import PrivateRoute from './components/nav/PrivateRoute'
import Navbar from './components/nav/Navbar'
import Home from './components/home/Home'
import Login from './components/auth/Login'
import Signup from './components/auth/Signup'
import AdminPage from './components/admin/AdminPage'
import UserPage from './components/user/UserPage'
import './components/styles/app.css'

function App() {
    return (
        <AuthProvider>
            <Router>
                <Navbar/>
                <Routes>
                    <Route path='/' element={<Home/>}/>
                    <Route path='/login' element={<Login/>}/>
                    <Route path='/signup' element={<Signup/>}/>
                    <Route path="/adminpage" element={<PrivateRoute><AdminPage/></PrivateRoute>}/>
                    <Route path="/userpage" element={<PrivateRoute><UserPage/></PrivateRoute>}/>
                    <Route path="*" element={<Navigate to="/"/>}/>
                </Routes>
            </Router>
        </AuthProvider>
    )
}

export default App
