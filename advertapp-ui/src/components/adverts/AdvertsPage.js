import React, {Component} from 'react'
import {Navigate} from 'react-router-dom'
import {Container} from 'semantic-ui-react'
import AdvertList from './AdvertList'
import AuthContext from '../auth/AuthContext'
import {advertApi} from '../util/AdvertApi'
import {handleLogError} from '../util/ErrorHandler'

class AdvertsPage extends Component {
    static contextType = AuthContext

    state = {
        adverts: [],
        advertId: '',
        isUser: true,
        isAdvertsLoading: false
    }

    componentDidMount() {
        const Auth = this.context
        const user = Auth.getUser()
        const isUser = user.role === 'USER'
        this.setState({isUser})

        this.handleGetAdverts()
    }

    handleInputChange = (e, {name, value}) => {
        this.setState({[name]: value})
    }

    handleGetAdverts = () => {
        const Auth = this.context
        const user = Auth.getUser()

        this.setState({isAdvertsLoading: true})
        advertApi.getAllAdverts(user)
            .then(response => {
                this.setState({adverts: response.data})
            })
            .catch(error => {
                handleLogError(error)
            })
            .finally(() => {
                this.setState({isAdvertsLoading: false})
            })
    }

    handleSearchAdvert = () => {
        const Auth = this.context
        const user = Auth.getUser()

        const id = this.state.advertId
        advertApi.getAdvert(user, id)
            .then(response => {
                const adverts = response.data
                this.setState({adverts})
            })
            .catch(error => {
                handleLogError(error)
                this.setState({adverts: []})
            })
    }

    render() {
        if (!this.state.isUser) {
            return <Navigate to='/'/>
        } else {
            const {isAdvertsLoading, adverts, advertId} = this.state
            return (
                <Container>
                    <AdvertList
                        isAdvertsLoading={isAdvertsLoading}
                        advertId={advertId}
                        adverts={adverts}
                        handleInputChange={this.handleInputChange}
                        handleSearchAdvert={this.handleSearchAdvert}
                    />
                </Container>
            )
        }
    }
}

export default AdvertsPage