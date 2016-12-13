import React from 'react';
import ReactDOM from 'react-dom';
import ReactTestUtils from 'react-addons-test-utils';
import {expect} from 'chai';
import navItems from '../../../src/components/navigation/navItems';
import SideBarMenu from '../../../src/components/navigation/SideBarMenu';


describe( 'SideBarMenu', () => {
    
    it( 'Menu rendering and navigation', () => {

        var activeMenuItemKey;
        var setActiveMenuItemKey = function( key ) {
            console.log( 'Navigation moved: key[' + key + ']' );
            activeMenuItemKey = key;
        }

        const component = ReactTestUtils.renderIntoDocument(
            <SideBarMenu
                activeMenuItemKey='5'
                onMenuItemSelect={setActiveMenuItemKey}
                navItems={navItems}/>
        );

        const menu = ReactTestUtils.scryRenderedDOMComponentsWithTag( component, 'li' );
        //console.log(menu[0].attributes);
        expect( menu.length ).to.equal( 10 );
        expect( $( menu[0] ).find( "a" )[0].textContent.trim() ).to.equal( 'Monitoring' );

        expect( menu[9].className.split( /\s+/ ) ).to.contain( 'active' );

        ReactTestUtils.Simulate.click( $( menu[1] ).find( "a" )[0] );
        expect( activeMenuItemKey ).to.equal( '1.1' );

        ReactTestUtils.Simulate.click( $( menu[4] ).find( "a" )[0] );
        expect( activeMenuItemKey ).to.equal( '2' );

        $( menu[0] ).find( ":first-child" ).trigger( "click" );
        
        ReactTestUtils.Simulate.click( $( menu[0] ).find( "a" )[0] );
        expect( activeMenuItemKey ).to.equal( '1' );
    });

});