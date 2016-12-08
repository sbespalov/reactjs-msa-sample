import React from 'react';
import ReactDOM from 'react-dom';
import {Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon} from 'react-bootstrap';
import { Link } from 'react-router'


import AppContext from 'AppContext';
import styles from 'components/navigation/sideBarMenu.module.css';

export default React.createClass( {

    getActiveMenuItemKey: function() {
        return this.props.activeMenuItemKey;
    },

    onSelect: function( activeMenuItemKey ) {
        if (activeMenuItemKey === this.getActiveMenuItemKey()){
            return;
        }
        this.props.onMenuItemSelect( activeMenuItemKey, this.navItemMap[activeMenuItemKey]);
        //this.props.dispatch(pushState(null, `/login?next=${redirectAfterLogin}`));        
    },

    addOnClickNavigation: function( component, routeLocation ) {
        if ( component.isOnClickNavigationEnabled ) {
            return;
        }
        component.isOnClickNavigationEnabled = true;
        var element = ReactDOM.findDOMNode( component );
        $( element ).find( "a" )[0].addEventListener( "click", ( evt ) => {
            this.onSelect( component.props.eventKey );
        });
    },

    renderNavItem: function(navItem, isNesteed){     
        var componentInstance = this;
        this.navItemMap = this.navItemMap || {};
        this.navItemMap[navItem['id']] = navItem['location'];
        if (navItem['items']){
            return <NavDropdown
                id={navItem['id']}
                key={navItem['id']}
                eventKey={navItem['id']}
                title={navItem['title']}
                role="menuitem"
                open={this.getActiveMenuItemKey() && this.getActiveMenuItemKey().startsWith( navItem['id'] ) }
                ref={( targetComponent ) => { targetComponent && componentInstance.addOnClickNavigation( targetComponent, navItem['location'] ) } }>
                    {
                        navItem['items'].map( (subItem) => {
                            return componentInstance.renderNavItem(subItem, true);
                        })
                    }
            </NavDropdown>;
        };
        if (isNesteed){
            return <MenuItem key={navItem['id']} eventKey={navItem['id']}>{navItem['title']}</MenuItem>;
        }
        return <NavItem key={navItem['id']} eventKey={navItem['id']}>{navItem['title']}</NavItem>;
    },
    
    render: function() {
        var userLabel = AppContext.getUser().firstName + ' ' + AppContext.getUser().lastName;
        
        return <div id="sidebar-menu" className={styles.bslSideBarMenuContainer}>
            <Navbar fluid className={styles.sidebar} >

                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="/">
                            {userLabel}
                        </a>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>

                <Navbar.Collapse>
                    <Navbar.Text className={styles.bslUserMenu}>
                        <Navbar.Link href="#/"><Glyphicon glyph="home"/></Navbar.Link>
                        <Navbar.Link href="#/logout"><Glyphicon glyph="log-out"/></Navbar.Link>
                    </Navbar.Text>
                    <Nav activeKey={this.getActiveMenuItemKey() } onSelect={this.onSelect}>
                    {
                        this.props.navItems.map( (navItem) => {
                            return this.renderNavItem(navItem);
                        })
                    }
                    </Nav>
                </Navbar.Collapse>

            </Navbar>
        </div>;
    }

});