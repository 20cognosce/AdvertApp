import React, {Component} from 'react'
import {Navigate} from 'react-router-dom'
import {Button, Dimmer, Form, Grid, Loader, Message, Segment} from 'semantic-ui-react'
import AuthContext from '../auth/AuthContext'
import {advertApi} from "../util/AdvertApi";
import {handleLogError} from "../util/ErrorHandler";

class AdvertsCreatePage extends Component {
    static contextType = AuthContext

    state = {
        title: '',
        county: '',
        city: '',
        district: '',
        street: '',
        houseNumber: '',
        description: '',

        isUser: true,

        isLoading: false,
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

    handleSubmit = async (e) => {
        e.preventDefault()
        const {title, description, county, city} = this.state

        if (!(title && description && county && city)) {
            this.setState({
                isError: true,
                errorMessage: 'Пожалуйста, предоставьте как минимум заголовок, описание, область и город'
            })
            return
        }

        const Auth = this.context
        const user = Auth.getUser()
        const advert = this.getBuiltAdvert(user.id)

        this.setState({isLoading: true})
        await advertApi.createAdvert(user, advert)
            .then(() => {
                this.clear()
                this.setState({isLoading: false})
            })
            .catch(error => {
                handleLogError(error)
            })
    }

    clear = () => {
        this.setState({
            title: '',
            description: '',
            address: null
        })
    }

    getBuiltAdvert = (id) => {
        return {
            title: this.state.title,
            description: this.state.description,
            userId: id,
            address:  {
                county: this.state.county,
                city: this.state.city,
                district: this.state.district,
                street: this.state.street,
                houseNumber: this.state.houseNumber
            }
        }
    }

    render() {
        const {isLoading, isUser, isError, errorMessage} = this.state

        if (isLoading) {
            return (
                <Segment basic style={{marginTop: window.innerHeight / 3}}>
                    <Dimmer active inverted>
                        <Loader inverted size='huge'>Создание объявления...</Loader>
                    </Dimmer>
                </Segment>
            )
        } else if (!isUser) {
            return <Navigate to='/'/>
        } else {
            return (
                <Grid textAlign='center'>
                    <Grid.Column style={{maxWidth: 750}}>
                        <Form size='large' onSubmit={this.handleSubmit}>
                            <Segment>
                                <Form.Input
                                    fluid
                                    autoFocus
                                    name='title'
                                    icon='user'
                                    iconPosition='left'
                                    placeholder='Заголовок объявления'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='county'
                                    icon='address card'
                                    iconPosition='left'
                                    placeholder='Область'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='city'
                                    icon='address card'
                                    iconPosition='left'
                                    placeholder='Город'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='district'
                                    icon='address card'
                                    iconPosition='left'
                                    placeholder='Район'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='street'
                                    icon='address card'
                                    iconPosition='left'
                                    placeholder='Улица'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='houseNumber'
                                    icon='address card'
                                    iconPosition='left'
                                    placeholder='Дом'
                                    onChange={this.handleInputChange}
                                />
                                <Form.TextArea
                                    fluid
                                    name='description'
                                    placeholder='Описание'
                                    onChange={this.handleInputChange}
                                />
                                <Form.Input
                                    fluid
                                    name='image'
                                    icon='lock'
                                    iconPosition='left'
                                    placeholder='Картинка...'
                                    onChange={this.handleInputChange}
                                />
                                <Button color='green' fluid size='huge'>Создать объявление</Button>
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