import React from 'react'
import {Grid, Header, Form, Icon, Image, Input, Item, Segment} from 'semantic-ui-react'

function AdvertList({isAdvertsLoading, advertTextSearch, adverts, handleInputChange, handleSearchAdvert}) {
    let advertsList
    if (adverts.length === 0) {
        advertsList = <Item key='no-advert'>Пока нет объявлений</Item>
    } else {
        advertsList = adverts.map(advert => {
            return (
                <Item key={advert.id}>
                    <Image src={`http://covers.openlibrary.org/b/isbn/${adverts.id}-M.jpg`} size='tiny' bordered
                           rounded/>
                    <Item.Content>
                        <Item.Header>{advert.title}</Item.Header>
                        <Item.Meta>{advert.id}</Item.Meta>
                        <Item.Description>
                            <Image src='https://react.semantic-ui.com/images/wireframe/short-paragraph.png'/>
                        </Item.Description>
                    </Item.Content>
                </Item>
            )
        })
    }

    return (
        <Segment loading={isAdvertsLoading} color='blue'>
            <Grid stackable divided>
                <Grid.Row columns='2'>
                    <Grid.Column width='3'>
                        <Header as='h2'>
                            <Icon name='book'/>
                            <Header.Content>Объявления</Header.Content>
                        </Header>
                    </Grid.Column>
                    <Grid.Column>
                        <Form onSubmit={handleSearchAdvert}>
                            <Input
                                action={{icon: 'search'}}
                                name='advertTestSearch'
                                placeholder='Search by ID or Title'
                                value={advertTextSearch}
                                onChange={handleInputChange}
                            />
                        </Form>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
            <Item.Group divided unstackable relaxed link>
                {advertsList}
            </Item.Group>
        </Segment>
    )
}

export default AdvertList