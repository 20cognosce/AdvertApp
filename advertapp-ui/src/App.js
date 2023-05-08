import React from 'react'
import {HashRouter, Navigate, Route, Routes} from 'react-router-dom'
import {AuthProvider} from './components/auth/AuthContext'
import PrivateRoute from './components/nav/PrivateRoute'
import Navbar from './components/nav/Navbar'
import Home from './components/home/Home'
import Login from './components/auth/Login'
import Signup from './components/auth/Signup'
import AdminPage from './components/admin/AdminPage'
import AdvertsPage from './components/adverts/AdvertsPage'
import AdvertsCreatePage from "./components/adverts/AdvertsCreatePage";

function App() {
    document.body.style.backgroundColor = "blanchedalmond"

    return (
        <AuthProvider>
            <HashRouter>
                <Navbar/>
                <Routes>
                    <Route path='/' element={<Home/>}/>
                    <Route path='/login' element={<Login/>}/>
                    <Route path='/signup' element={<Signup/>}/>
                    <Route path="/admin" element={<PrivateRoute><AdminPage/></PrivateRoute>}/>
                    <Route path="/adverts" element={<PrivateRoute><AdvertsPage/></PrivateRoute>}/>
                    <Route path="/adverts/new" element={<PrivateRoute><AdvertsCreatePage/></PrivateRoute>}/>
                    <Route path="*" element={<Navigate to="/"/>}/>
                </Routes>
            </HashRouter>
        </AuthProvider>
    )
}

export default App
