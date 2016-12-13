import React from 'react';
import ReactTestUtils from 'react-addons-test-utils';
import {expect} from 'chai';
import RecalculationFilterForm from 'components/recalculation/RecalculationFilterForm';

describe( 'RecalculationFilterForm', () => {

    it( 'Get form values', () => {
        const component = ReactTestUtils.renderIntoDocument(
            <RecalculationFilterForm/>
        );

        const formInputs = ReactTestUtils.scryRenderedDOMComponentsWithTag( component, 'input' );
        expect( formInputs.length ).to.equal( 3 );
    });

});