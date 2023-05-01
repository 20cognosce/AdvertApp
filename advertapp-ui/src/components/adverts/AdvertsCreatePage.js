import React, {Component} from 'react'
import {Navigate, NavLink} from 'react-router-dom'
import {Button, Container, Form, Grid, Message, Segment} from 'semantic-ui-react'
import AuthContext from '../auth/AuthContext'
import {advertApi} from "../util/AdvertApi";
import {handleLogError} from "../util/ErrorHandler";

class AdvertsCreatePage extends Component {
    static contextType = AuthContext

    state = {
        title: '',
        description: '',
        userId: null,
        address: null,
        isUser: true,
        isError: false,
        errorMessage: ''
    }

    componentDidMount() {
        const Auth = this.context
        const user = Auth.getUser()
        const isUser = user.role === 'USER'
        const userId = user.id

        this.setState({isUser})
        this.setState({userId})
    }

    handleInputChange = (e, {name, value}) => {
        this.setState({[name]: value})
    }

    handleSubmit = (e) => {
        e.preventDefault()
        const {firstName, lastName, email, password} = this.state

        if (!(firstName && lastName && email && password)) {
            this.setState({
                isError: true,
                errorMessage: 'Please, fill in all fields!'
            })
            return
        }

        const user = {firstName, lastName, email, password}

        advertApi.signup(user)
            .then(response => {
                const {id, firstName, lastName, role} = response.data
                const authdata = window.btoa(email + ':' + password)
                const user = {id, firstName, lastName, role, authdata}

                const Auth = this.context
                Auth.userLogin(user)

                this.setState({
                    email: '',
                    password: '',
                    isLoggedIn: true,
                    isError: false,
                    errorMessage: ''
                })
            })
            .catch(error => {
                handleLogError(error)
                if (error.response && error.response.data) {
                    const errorData = error.response.data
                    let errorMessage = 'Invalid fields'
                    if (errorData.status === 409) {
                        errorMessage = errorData.message
                    } else if (errorData.status === 400) {
                        errorMessage = errorData.errors[0].defaultMessage
                    }
                    this.setState({
                        isError: true,
                        errorMessage
                    })
                }
            })
    }

    render() {
        const {isUser, userId, isError, errorMessage} = this.state

        if (!isUser) {
            return <Navigate to='/'/>
        } else {
            return (
                <Grid textAlign='center'>
                    <Grid.Column style={{maxWidth: 450}}>
                        <Form size='large' onSubmit={this.handleSubmit}>
                            <Segment>
                                <Form.Input
                                    fluid
                                    autoFocus
                                    name='firstName'
                                    icon='user'
                                    iconPosition='left'
                                    placeholder='Заголовок объявления'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='lastName'
                                    icon='address card'
                                    iconPosition='left'
                                    placeholder='Адрес'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='email'
                                    icon='at'
                                    iconPosition='left'
                                    placeholder='Описание'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='password'
                                    icon='lock'
                                    iconPosition='left'
                                    placeholder='Картинка...'
                                    type='password'
                                    onChange={this.handleInputChange}
                                />
                                <Button color='blue' fluid size='large'>Создать объявление</Button>
                            </Segment>
                        </Form>

                        {isError && <Message negative>{errorMessage}</Message>}
                    </Grid.Column>
                </Grid>
            )
        }
    }
}

export default AdvertsCreatePage