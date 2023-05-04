import React, {Component} from 'react'
import {Navigate, NavLink} from 'react-router-dom'
import {Button, Container, Form, Grid, Input, Message, Segment, Table} from 'semantic-ui-react'
import AuthContext from '../auth/AuthContext'
import {advertApi} from "../util/AdvertApi";
import {handleLogError} from "../util/ErrorHandler";
import AdvertForm from "./AdvertForm";

class AdvertsCreatePage extends Component {
    static contextType = AuthContext

    state = {
        advertId,
        advertTitle,

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


    handleCreateAdvert = () => {
        const Auth = this.context
        const user = Auth.getUser()

        let {bookIsbn, bookTitle} = this.state
        bookIsbn = bookIsbn.trim()
        bookTitle = bookTitle.trim()
        if (!(bookIsbn && bookTitle)) {
            return
        }

        const book = {isbn: bookIsbn, title: bookTitle}
        advertApi.addBook(user, book)
            .then(() => {
                this.clearBookForm()
                this.handleGetBooks()
            })
            .catch(error => {
                handleLogError(error)
            })
    }

    clearAdvertForm = () => {
        this.setState({
            advertId: null,
            advertTitle: null
        })
    }

    render() {
        const {isUser, userId, isError, errorMessage} = this.state

        return (
            <>
                <Grid stackable divided>
                    <Grid.Row columns='2'>
                        <Grid.Column width='5'>
                            <AdvertForm
                                advertId={advertId}
                                advertTitle={advertTitle}
                                handleInputChange={handleInputChange}
                                handleCreateAdvert={handleCreateAdvert}
                            />
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
                <Table compact striped selectable>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell width={1}/>
                            <Table.HeaderCell width={3}>Cover</Table.HeaderCell>
                            <Table.HeaderCell width={4}>ID</Table.HeaderCell>
                            <Table.HeaderCell width={8}>Title</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {advertList}
                    </Table.Body>
                </Table>
            </>
        )

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