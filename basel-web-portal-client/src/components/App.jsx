import React from 'react';
import ReactDOM from 'react-dom';
import styles from './app.css';
import {Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon} from 'react-bootstrap';
import SideBarMenu from './navigation/SideBarMenu';
import { hashHistory } from 'react-router'

export default React.createClass( {

    addOnClickNavigation: function( component, route ) {
        if (component.onClickNavigation){
            return;
        }
        component.onClickNavigation = () => {
            hashHistory.push( route );
        }
        var element = ReactDOM.findDOMNode( component );
        element.addEventListener( 'click', ( evt ) => {
            component.onClickNavigation();
        }, false );
    },

    render: function() {

        var componentInstance = this;

        return <div className={styles.bslPageContainer}>
            <SideBarMenu/>
            <div className={styles.bslPageContent + ' ' + styles.main}>{this.props.children}</div>
        </div>;
    }
});