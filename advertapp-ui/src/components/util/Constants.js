const prod = {
    url: {
        API_BASE_URL: 'docker-compose/backend-url'
    }
}

const dev = {
    url: {
        API_BASE_URL: 'http://localhost:8080'
    }
}

export const config = process.env.NODE_ENV === 'development' ? dev : prod