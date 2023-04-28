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
        titleToSearch: '',
        isUser: true,
        isAdvertsLoading: false
    }

    componentDidMount() {
        const Auth = this.context
        const user = Auth.getUser()
        const isUser = user.role === 'USER'

        this.setState({isUser})
        this.handleGetAllAdverts()
    }

    handleInputChange = (e, {name, value}) => {
        this.setState({[name]: value})
    }

    handleGetAllAdverts = () => {
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

        const title = this.state.titleToSearch
        this.setState({isAdvertsLoading: true})
        advertApi.searchAdvertByTitle(user, title)
            .then(response => {
                const adverts = response.data
                this.setState({adverts})
            })
            .catch(error => {
                handleLogError(error)
                this.setState({adverts: []})
            })
            .finally(() => {
                this.setState({isAdvertsLoading: false})
            })
    }

    render() {
        if (!this.state.isUser) {
            return <Navigate to='/'/>
        } else {
            const {isAdvertsLoading, adverts, titleToSearch} = this.state
            return (
                <Container>
                    <AdvertList
                        isAdvertsLoading={isAdvertsLoading}
                        titleToSearch={titleToSearch}
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