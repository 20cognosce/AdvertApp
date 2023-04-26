import axios from 'axios'
import { config } from './Constants'

export const advertApi = {
    authenticate,
    signup,
    numberOfUsers,
    numberOfAdverts,
    getUsers,
    deleteUser,
    getAdverts,
    getAdvert,
    deleteAdvert,
    addAdvert
}

function authenticate(username, password) {
    return instance.post('/auth/authenticate', { username, password }, {
        headers: { 'Content-type': 'application/json' }
    })
}

function signup(user) {
    return instance.post('/auth/signup', user, {
        headers: { 'Content-type': 'application/json' }
    })
}

function numberOfUsers() {
    return instance.get('/users/count')
}

function numberOfAdverts() {
    return instance.get('/adverts/count')
}

function getUsers(user, id) {
    const url = id ? `/users/${id}` : '/users'
    return instance.get(url, {
        headers: { 'Authorization': basicAuth(user) }
    })
}

function deleteUser(user, id) {
    return instance.delete(`/users/${id}`, {
        headers: { 'Authorization': basicAuth(user) }
    })
}

function getAdverts(user) {
    const url = `/adverts`
    return instance.get(url, {
        headers: { 'Authorization': basicAuth(user) }
    })
}

function getAdvert(user, id) {
    const url = id ? `/adverts/${id}` : '/adverts'
    return instance.get(url, {
        headers: { 'Authorization': basicAuth(user) }
    })
}

function deleteAdvert(user, id) {
    return instance.delete(`/adverts/${id}`, {
        headers: { 'Authorization': basicAuth(user) }
    })
}

function addAdvert(user, advert) {
    return instance.post('/adverts', advert, {
        headers: {
            'Content-type': 'application/json',
            'Authorization': basicAuth(user)
        }
    })
}

const instance = axios.create({
    baseURL: config.url.API_BASE_URL
})

function basicAuth(user) {
    return `Basic ${user.authdata}`
}