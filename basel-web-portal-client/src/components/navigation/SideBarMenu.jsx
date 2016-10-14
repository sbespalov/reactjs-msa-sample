import React from 'react';
import ReactDOM from 'react-dom';
import styles from './sideBarMenu.css';
import {Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon} from 'react-bootstrap';
import { hashHistory } from 'react-router'

export default React.createClass( {

    getActiveMenuItemKey: function() {
        return this.props.activeMenuItemKey || 1;
    },
    
    onSelect: function(activeMenuItemKey){
        this.props.onMenuItemSelect(activeMenuItemKey);
    },
    
    addOnClickNavigation: function( component, route ) {
        if ( component.onClickNavigation ) {
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

        return <div id="sidebar-menu" className={styles.bslSideBarMenuContainer}>
            <Navbar fluid className={styles.sidebar} >

                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="/">Ф.Киркоров </a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>

                <Navbar.Collapse>
                    <Navbar.Text className={styles.bslUserMenu}>
                        <Navbar.Link href="#/"><Glyphicon glyph="home"/></Navbar.Link>
                        <Navbar.Link href="#/logout"><Glyphicon glyph="log-out"/></Navbar.Link>
                    </Navbar.Text>
                    <Nav activeKey={this.getActiveMenuItemKey()} onSelect={this.onSelect}>
                        <NavDropdown eventKey={1} title="Monitoring" id="basic-nav-dropdown" ref={( targetComponent ) => { targetComponent ? componentInstance.addOnClickNavigation( targetComponent, '/monitoring' ) : {}; } }>
                            <MenuItem eventKey={1.1} href="#/security-recalculate">Security recalculate</MenuItem>
                        </NavDropdown>
                        <NavItem eventKey={2}>Reports</NavItem>
                        <NavItem eventKey={3}>References</NavItem>
                    </Nav>
                </Navbar.Collapse>

            </Navbar>
        </div>;
    }
    
});