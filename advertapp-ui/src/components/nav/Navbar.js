import React from 'react'
import {Link} from 'react-router-dom'
import {Container, Menu} from 'semantic-ui-react'
import {useAuth} from '../auth/AuthContext'

function Navbar() {
    const {getUser, userIsAuthenticated, userLogout} = useAuth()

    const logout = () => {
        userLogout()
    }

    const enterMenuStyle = () => {
        return userIsAuthenticated() ? {"display": "none"} : {"display": "block"}
    }

    const logoutMenuStyle = () => {
        return userIsAuthenticated() ? {"display": "block"} : {"display": "none"}
    }

    const adminPageStyle = () => {
        const user = getUser()
        return user && user.role === 'ADMIN' ? {"display": "block"} : {"display": "none"}
    }

    const userPageStyle = () => {
        const user = getUser()
        return user && user.role === 'USER' ? {"display": "block"} : {"display": "none"}
    }

    const getUserFirstName = () => {
        const user = getUser()
        return user ? user.firstName : ''
    }

    return (
        <Menu stackable size='massive' color={'violet'} inverted>
            <Container>
                <Menu.Item header>AdvertApp-UI</Menu.Item>
                <Menu.Item as={Link} exact='true' to="/">Домой</Menu.Item>
                <Menu.Item as={Link} to="/admin" style={adminPageStyle()}>Администрирование</Menu.Item>
                <Menu.Item as={Link} to="/adverts" style={userPageStyle()}>Объявления</Menu.Item>
                <Menu.Item as={Link} to="/adverts/new" style={userPageStyle()}>Разместить объявление</Menu.Item>

                <Menu.Menu position='right'>
                    <Menu.Item as={Link} to="/login" style={enterMenuStyle()}>Вход</Menu.Item>
                    <Menu.Item as={Link} to="/signup" style={enterMenuStyle()}>Регистрация</Menu.Item>
                    <Menu.Item header style={logoutMenuStyle()}>{`Здравствуйте, ${getUserFirstName()}`}</Menu.Item>
                    <Menu.Item as={Link} to="/" style={logoutMenuStyle()} onClick={logout}>Выйти</Menu.Item>
                </Menu.Menu>
            </Container>
        </Menu>
    )
}

export default Navbar
