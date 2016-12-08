import React from 'react';
import ReactDOM from 'react-dom';
import ReactTestUtils from 'react-addons-test-utils';
import {expect} from 'chai';

import navItems from '../../../src/components/navigation/navItems';
import SideBarMenu from '../../../src/components/navigation/SideBarMenu';


describe( 'SideBarMenu', () => {

    it( 'renders a Navigation Items', () => {

        var setActiveMenuItemKey = function( activeMenuItemKey ) {
            console.log( activeMenuItemKey );
        }

        const component = ReactTestUtils.renderIntoDocument(
            <SideBarMenu
                activeMenuItemKey='1'
                onMenuItemSelect={setActiveMenuItemKey}
                navItems={navItems}/>
        );

        const menu = ReactTestUtils.scryRenderedDOMComponentsWithTag( component, 'li' );
        expect( menu.length ).to.equal( 10 );
        expect( $( menu[0] ).find( "a" )[0].textContent.trim() ).to.equal( 'Monitoring' );

        ReactTestUtils.Simulate.click( $( menu[0] ).find( "a" )[0] );
        ReactTestUtils.Simulate.click( $( menu[1] ).find( "a" )[0] );
    });

});