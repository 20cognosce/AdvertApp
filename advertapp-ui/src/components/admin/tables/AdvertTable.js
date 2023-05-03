import React from 'react'
import {Button, Form, Grid, Image, Input, Table} from 'semantic-ui-react'
import AdvertForm from "../AdvertForm";

function AdvertTable({
                         handleInputChange,
                         adverts,
                         advertId,
                         advertTitle,
                         advertTitleToFind,
                         handleFindAdverts,
                         handleGetImageUrlById,
                         handleCreateAdvert,
                         handleDeleteAdvert}) {
    let advertList
    if (adverts.length === 0) {
        advertList = (
            <Table.Row key='no-advert'>
                <Table.Cell collapsing textAlign='center' colSpan='4'>Пока нет объявлений</Table.Cell>
            </Table.Row>
        )
    } else {
        advertList = adverts.map(advert => {
            return (
                <Table.Row key={advert.id}>
                    <Table.Cell collapsing>
                        <Button
                            circular
                            color='red'
                            size='small'
                            icon='trash'
                            onClick={() => handleDeleteAdvert(advert.id)}
                        />
                    </Table.Cell>
                    <Table.Cell>
                        <Image src={handleGetImageUrlById(advert.images[0].id)} size='tiny' bordered
                               rounded/>
                    </Table.Cell>
                    <Table.Cell>{advert.id}</Table.Cell>
                    <Table.Cell>{advert.title}</Table.Cell>
                </Table.Row>
            )
        })
    }

    return (
        <>
            <Grid stackable divided>
                <Grid.Row columns='2'>
                    <Grid.Column width='5'>
                        <Form onSubmit={handleFindAdverts}>
                            <Input
                                action={{icon: 'search'}}
                                name='advertTitleToFind'
                                placeholder='Поиск по заголовку'
                                value={advertTitleToFind}
                                onChange={handleInputChange}
                            />
                        </Form>
                    </Grid.Column>
                    <Grid.Column>
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
}

export default AdvertTable