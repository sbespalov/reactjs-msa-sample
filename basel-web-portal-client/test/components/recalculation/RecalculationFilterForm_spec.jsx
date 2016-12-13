import React from 'react';
import ReactTestUtils from 'react-addons-test-utils';
import {expect} from 'chai';
import RecalculationFilterForm from 'components/recalculation/RecalculationFilterForm';

describe( 'RecalculationFilterForm', () => {

    it( 'Get form values', () => {
        var tree = ReactTestUtils.renderIntoDocument(
            <RecalculationFilterForm/>
        );

        var formInputs = ReactTestUtils.scryRenderedDOMComponentsWithTag( tree, 'input' );
        expect( formInputs.length ).to.equal( 3 );

        var formValue = {
            calculationDetail: 'calculationDetail',
            dateFrom: '2017/01/01 00:00:00',
            dateTo: '2017/01/01 00:00:00'
        }

        var componentInstance = ReactTestUtils.findRenderedComponentWithType( tree, RecalculationFilterForm );
        componentInstance.setValue( formValue );

        expect( componentInstance.getValue() ).to.deep.equal( formValue );
    });

});