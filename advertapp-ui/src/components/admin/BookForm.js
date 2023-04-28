import React from 'react'
import {Button, Form, Icon} from 'semantic-ui-react'

function AdvertForm({advertId, advertTitle, handleInputChange, handleAddBook}) {
    const createBtnDisabled = advertId.trim() === '' || advertTitle.trim() === ''
    return (
        <Form onSubmit={handleAddBook}>
            <Form.Group>
                <Form.Input
                    name='bookIsbn'
                    placeholder='ISBN *'
                    value={advertId}
                    onChange={handleInputChange}
                />
                <Form.Input
                    name='bookTitle'
                    placeholder='Title *'
                    value={advertId}
                    onChange={handleInputChange}
                />
                <Button icon labelPosition='right' disabled={createBtnDisabled}>
                    Create<Icon name='add'/>
                </Button>
            </Form.Group>
        </Form>
    )
}

export default AdvertForm