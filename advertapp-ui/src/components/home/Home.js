import React, {Component} from 'react'
import {Statistic, Icon, Grid, Container, Segment, Dimmer, Loader, Header} from 'semantic-ui-react'
import {advertApi} from '../util/AdvertApi'
import {handleLogError} from '../util/ErrorHandler'

class Home extends Component {
    state = {
        usersCount: 0,
        advertsCount: 0,
        isLoading: false
    }

    async componentDidMount() {
        this.setState({isLoading: true})
        try {
            let response = await advertApi.getUsersCount()
            const usersCount = response.data

            response = await advertApi.getAdvertsCount()
            const advertsCount = response.data

            this.setState({usersCount, advertsCount})
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
                    <Dimmer active inverted page={true}>
                        <Loader inverted size='huge'>Loading</Loader>
                    </Dimmer>
                </Segment>
            )
        } else {
            const {usersCount, advertsCount} = this.state
            return (
                <Container text>
                    <Grid stackable columns={2} style={{ height: '90vh' }}>
                        <Grid.Row style={{ flexGrow: 1 }}>
                            <Grid.Column textAlign='center'>
                                <Segment color='blue'>
                                    <Statistic size='massive'>
                                        <Statistic.Value>
                                            <Icon name='users' color='black'/>
                                            <span> </span>
                                            {usersCount}
                                        </Statistic.Value>
                                        <Statistic.Label>
                                            <Header as='h2'>
                                                Зарегистрированных пользователей
                                            </Header>
                                        </Statistic.Label>
                                    </Statistic>
                                </Segment>
                            </Grid.Column>

                            <Grid.Column textAlign='center'>
                                <Segment color='blue'>
                                    <Statistic size='massive'>
                                        <Statistic.Value>
                                            <Icon name='newspaper' color='black'/>
                                            <span> </span>
                                            {advertsCount}
                                        </Statistic.Value>
                                        <Statistic.Label>
                                            <Header as='h2'>
                                                Размещенных объявлений
                                            </Header>
                                        </Statistic.Label>
                                    </Statistic>
                                </Segment>
                            </Grid.Column>
                        </Grid.Row>

                        <Grid.Row color={"violet"} style={{alignSelf: 'flex-end', borderRadius: 20}} stretched>
                            <Grid.Column width={16}>
                                <Header as='h5' inverted color={"black"} textAlign={"center"}>
                                    Данное приложение создано в рамках курсовой работы по дисциплине
                                    <p>
                                        "Разработка клиент-серверных приложений".
                                    </p>
                                    <p>
                                        Разработчик — студент группы ИКБО-24-20, Верт Дмитрий Андреевич.
                                    </p>
                                </Header>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                </Container>
            )
        }
    }
}

export default Home