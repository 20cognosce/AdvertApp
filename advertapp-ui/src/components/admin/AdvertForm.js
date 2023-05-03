import React from 'react'
import {Button, Form, Icon} from 'semantic-ui-react'

function AdvertForm({advertId, advertTitle, handleInputChange, handleCreateAdvert}) {
    const createBtnDisabled = advertId.trim() === '' || advertTitle.trim() === ''
    return (
        <Form onSubmit={handleCreateAdvert}>
            <Form.Group>
                <Form.Input
                    name='advertId'
                    placeholder='ID *'
                    value={advertId}
                    onChange={handleInputChange}
                />
                <Form.Input
                    name='advertTitle'
                    placeholder='Title *'
                    value={advertTitle}
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