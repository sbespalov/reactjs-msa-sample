import React from 'react';
import {connect} from 'react-redux';
import {Glyphicon, FormGroup, Col, ControlLabel, FormControl, InputGroup} from 'react-bootstrap';
import CommonForm from 'components/common/CommonForm';

export default class RecalculationFilter extends React.Component {

    getValue() {
        return this.refs.filterDetailForm.getValue();
    }

    setValue( val ) {
        this.refs.filterDetailForm.setValue( val );
    }

    render() {
        return <CommonForm horizontal ref="filterDetailForm">
            <FormGroup>
                <Col componentClass={ControlLabel} md={2}>
                    Detail
                </Col>
                <Col md={10}>
                    <FormControl type="text" placeholder="Code" id="bookCode"/>
                </Col>
            </FormGroup>
            <FormGroup>
                <Col componentClass={ControlLabel} md={2}>
                    Detail
                </Col>
                <Col md={10}>
                    <FormControl type="text" placeholder="Source System" id="sourceSystem"/>
                </Col>
            </FormGroup>
            <FormGroup>
                <Col componentClass={ControlLabel} md={2}>
                    Detail
                </Col>
                <Col md={10}>
                    <FormControl type="text" placeholder="Book Type" id="bookType"/>
                </Col>
            </FormGroup>
        </CommonForm>;
    }

}