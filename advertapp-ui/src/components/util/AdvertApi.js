import axios from 'axios'
import {config} from './Constants'

export const advertApi = {
    login,
    signup,
    getUsersCount,
    getAdvertsCount,
    getAllAdverts,
    searchAdvertByTitle
}

function signup(user) {
    return instance.post('/auth/signup', user, {
        headers: { 'Content-type': 'application/json' }
    })
}

function login(email, password) {
    return instance.post('/auth/login', { email, password }, {
        headers: { 'Content-type': 'application/json' }
    })
}

function getUsersCount() {
    return instance.get('/users/count')
}

function getAdvertsCount() {
    return instance.get('/adverts/count')
}

function getAllAdverts(user) {
    const url = `/adverts`
    return instance.get(url, {
        headers: { 'Authorization': basicAuth(user) }
    })
}

function searchAdvertByTitle(user, title) {
    const url = title ? `/adverts?title=${title}` : '/adverts';
    return instance.get(url, {
        headers: {'Authorization': basicAuth(user)}
    });
}

//-----------------------------------------------------------------------------------

function getUser(user, id) {
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