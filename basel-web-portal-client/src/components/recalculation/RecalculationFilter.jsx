import React from 'react';
import {connect} from 'react-redux';
import {Glyphicon, FormGroup, Col, ControlLabel, FormControl, InputGroup} from 'react-bootstrap';
import "bootstrap-datetimepicker/src/js/bootstrap-datetimepicker.js";
import "bootstrap-datetimepicker/css/bootstrap-datetimepicker.css";
import CommonForm from 'components/common/CommonForm';

export default class RecalculationFilter extends React.Component {

    getValue(){
        return this.refs.resultListFilterDetailForm.getValue();
    }

    setValue(val){
        this.refs.resultListFilterDetailForm.setValue(val);
    }
    
    render() {
        return <CommonForm horizontal ref="resultListFilterDetailForm">
            <FormGroup>
                <Col componentClass={ControlLabel} md={2}>
                    Detail
                </Col>
                <Col md={10}>
                    <FormControl type="text" placeholder="Bid/Ask Calculate Detail" id="calculationDetail"/>
                </Col>
            </FormGroup>

            <FormGroup>
                <Col componentClass={ControlLabel} md={2}>
                    Date
                </Col>
                <Col md={5}>
                    <InputGroup
                        ref={( targetComponent ) => {
                            var targetElement = ReactDOM.findDOMNode( targetComponent );
                            $( targetElement ).datetimepicker( {
                                viewMode: 'days',
                                format: 'YYYY/MM/DD HH:mm:ss'
                            });

                        } }>

                        <FormControl type="text" placeholder="From" id="dateFrom"/>
                        <InputGroup.Addon>
                            <Glyphicon glyph="calendar" />
                        </InputGroup.Addon>
                    </InputGroup>
                </Col>
                <Col md={5}>
                    <InputGroup
                        ref={( targetComponent ) => {
                            var targetElement = ReactDOM.findDOMNode( targetComponent );
                            $( targetElement ).datetimepicker( {
                                viewMode: 'days',
                                format: 'YYYY/MM/DD HH:mm:ss'
                            });

                        } }>

                        <FormControl type="text" placeholder="To" id="dateTo"/>
                        <InputGroup.Addon>
                            <Glyphicon glyph="calendar" />
                        </InputGroup.Addon>
                    </InputGroup>
                </Col>
            </FormGroup>
        </CommonForm>;
    }

}