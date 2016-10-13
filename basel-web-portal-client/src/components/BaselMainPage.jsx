import React from 'react';
import styles from './app.css';
import {Accordion, Panel, Table, ButtonGroup, Button, MenuItem, Nav, NavItem, Navbar, NavDropdown, Glyphicon} from 'react-bootstrap';

export default React.createClass( {

    render: function() {
        return <div className={styles.bslPageContainer}>
            <div className={styles.bslSideBarMenuContainer}>
                <Navbar fluid className={styles.sidebar}>

                    <Navbar.Header>
                        <Navbar.Brand>
                            <a href="#">Ф.Киркоров </a>
                        </Navbar.Brand>
                        <Navbar.Toggle />
                    </Navbar.Header>

                    <Navbar.Collapse>
                        <Navbar.Text className={styles.bslUserMenu}>
                            <Navbar.Link href="#"><Glyphicon glyph="home"/></Navbar.Link>
                            <Navbar.Link href="#"><Glyphicon glyph="log-out"/></Navbar.Link>
                        </Navbar.Text>
                        <Nav>
                            <NavDropdown eventKey={1} title="Monitoring" id="basic-nav-dropdown" animation={true}>
                                <MenuItem eventKey={1.1}>Action</MenuItem>
                            </NavDropdown>
                            <NavItem eventKey={2}>Reports</NavItem>
                            <NavItem eventKey={3}>References</NavItem>
                        </Nav>
                    </Navbar.Collapse>

                </Navbar>
            </div>
            <div className={styles.bslPageContent + ' ' + styles.main}>bsl-page-content</div>
        </div>;
    }

});