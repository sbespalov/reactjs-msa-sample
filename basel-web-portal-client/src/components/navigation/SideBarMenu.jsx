import React from 'react';
import ReactDOM from 'react-dom';
import {Nav, NavItem, Navbar, NavDropdown, MenuItem, Glyphicon} from 'react-bootstrap';
import { hashHistory } from 'react-router'
import styles from 'components/navigation/sideBarMenu.module.css';

export default React.createClass( {

    getActiveMenuItemKey: function() {
        return this.props.activeMenuItemKey;
    },

    onSelect: function( activeMenuItemKey ) {
        this.props.onMenuItemSelect( activeMenuItemKey );
        hashHistory.push(this.navItemMap[activeMenuItemKey]);
    },

    addOnClickNavigation: function( component, routeLocation ) {
        if ( component.onClickNavigation ) {
            return;
        }
        component.onClickNavigation = () => {
            hashHistory.push( routeLocation );
        }
        var element = ReactDOM.findDOMNode( component );
        element.addEventListener( 'click', ( evt ) => {
            component.onClickNavigation();
            this.onSelect( component.props.eventKey );
        }, false );
    },

    renderNavItem: function(navItem, isNesteed){
        var componentInstance = this;
        this.navItemMap = this.navItemMap || {};
        this.navItemMap[navItem.get('id')] = navItem.get('location');
        if (navItem.get('items')){
            return <NavDropdown
                eventKey={navItem.get('id')}
                title={navItem.get('title')}
                role="menuitem"
                open={this.getActiveMenuItemKey() && this.getActiveMenuItemKey().startsWith( navItem.get('id') ) }
                ref={( targetComponent ) => { targetComponent && componentInstance.addOnClickNavigation( targetComponent, navItem.get('location') ) } }>
                    {
                        navItem.get('items').map( (subItem) => {
                            return componentInstance.renderNavItem(subItem, true);
                        })
                    }
            </NavDropdown>;
        };
        if (isNesteed){
            return <MenuItem eventKey={navItem.get('id')}>{navItem.get('title')}</MenuItem>;
        }
        return <NavItem eventKey={navItem.get('id')}>{navItem.get('title')}</NavItem>;
    },
    
    render: function() {
        return <div id="sidebar-menu" className={styles.bslSideBarMenuContainer}>
            <Navbar fluid className={styles.sidebar} >

                <Navbar.Header>
                    <Navbar.Brand>
                        <a href="/">Ф.Киркоров</a>
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