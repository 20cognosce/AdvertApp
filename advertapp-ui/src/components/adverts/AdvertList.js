import React from 'react'
import {Grid, Header, Form, Icon, Image, Input, Item, Segment} from 'semantic-ui-react'

function AdvertList({isAdvertsLoading, titleToSearch, adverts, handleInputChange, handleSearchAdvert}) {
    let advertsList
    if (adverts.length === 0) {
        advertsList = <Item key='no-advert'>Пока нет объявлений</Item>
    } else {
        advertsList = adverts.map(advert => {
            let address = Object.values(advert.address)
            address.shift()

            return (
                <Item key={advert.id}>
                    <Image src={`http://localhost:8080/images/${advert.images[0].id}`}
                           size='large' bordered rounded/>
                    <Item.Content>
                        <Item.Header>{advert.title}</Item.Header>
                        <Item.Meta>#{advert.id}</Item.Meta>
                        <Item.Meta>{address.join(", ")}</Item.Meta>
                        <Item.Description>
                            {advert.description}
                        </Item.Description>
                    </Item.Content>
                </Item>
            )
        })
    }

    return (
        <Segment loading={isAdvertsLoading} color='blue'>
            <Grid stackable>
                <Grid.Row columns='2'>
                    <Grid.Column floated='left'>
                        <Header as='h1'>
                            <Icon name='newspaper outline'/>
                            <Header.Content>Объявления</Header.Content>
                        </Header>
                    </Grid.Column>

                    <Grid.Column floated='right' width={4}>
                        <Form onSubmit={handleSearchAdvert}>
                            <Input
                                fluid
                                action={{icon: 'search'}}
                                name='titleToSearch'
                                placeholder='Поиск по названию'
                                value={titleToSearch}
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