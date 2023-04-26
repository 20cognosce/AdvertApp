import React, {Component} from 'react'
import {Statistic, Icon, Grid, Container, Image, Segment, Dimmer, Loader} from 'semantic-ui-react'
import {advertApi} from '../util/AdvertApi'
import {handleLogError} from '../util/ErrorHandler'

class Home extends Component {
    state = {
        numberOfUsers: 0,
        numberOfAdverts: 0,
        isLoading: false
    }

    async componentDidMount() {
        this.setState({isLoading: true})
        try {
            let response = await advertApi.numberOfUsers()
            const numberOfUsers = response.data

            response = await advertApi.numberOfAdverts()
            const numberOfAdverts = response.data

            this.setState({numberOfUsers, numberOfAdverts})
        } catch (error) {
            handleLogError(error)
        } finally {
            this.setState({isLoading: false})
        }
    }

    render() {
        const {isLoading} = this.state
        if (isLoading) {
            return (
                <Segment basic style={{marginTop: window.innerHeight / 2}}>
                    <Dimmer active inverted>
                        <Loader inverted size='huge'>Loading</Loader>
                    </Dimmer>
                </Segment>
            )
        } else {
            const {numberOfUsers, numberOfAdverts} = this.state
            return (
                <Container text>
                    <Grid stackable columns={2}>
                        <Grid.Row>

                            <Grid.Column textAlign='center'>
                                <Segment color='blue'>
                                    <Statistic size='huge'>
                                        <Statistic.Value>
                                            <Icon name='users' color='black'/>
                                            {numberOfUsers}
                                        </Statistic.Value>
                                        <br></br>
                                        <Statistic.Label>Users</Statistic.Label>
                                    </Statistic>
                                </Segment>
                            </Grid.Column>

                            <Grid.Column textAlign='center'>
                                <Segment color='blue'>
                                    <Statistic size='huge'>
                                        <Statistic.Value>
                                            <Icon name='newspaper' color='black'/>
                                            {numberOfAdverts}
                                        </Statistic.Value>
                                        <br></br>
                                        <Statistic.Label>Adverts</Statistic.Label>
                                    </Statistic>
                                </Segment>
                            </Grid.Column>

                        </Grid.Row>
                    </Grid>
                </Container>
            )
        }
    }
}

export default Home