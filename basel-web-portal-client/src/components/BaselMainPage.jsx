import React from 'react';
import styles from './app.css';
import {Accordion, Panel, Table, ButtonGroup, Button, MenuItem, Nav, NavItem} from 'react-bootstrap';

export default React.createClass( {

    render: function() {
        return <div className={styles.bslPageContainer}>
            <div className={styles.bslSideBarMenuContainer}>
                <Accordion>
                    <Panel header={<Button bsStyle="link">Position</Button>} eventKey="1">
                        blabla
                    </Panel>
                    <Panel header={<NavItem eventKey={1} title="Item">NavItem 1 content</NavItem>} eventKey="2">
                        <Table responsive>
                            <tbody>
                                <tr>
                                    <td>
                                        <MenuItem>link</MenuItem>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <MenuItem>link</MenuItem>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <MenuItem>link</MenuItem>
                                    </td>
                                </tr>
                            </tbody>
                        </Table>
                        <Nav bsStyle="pills" stacked activeKey={1}>
                            <NavItem eventKey={1} title="Item">NavItem 1 content</NavItem>
                            <NavItem eventKey={2} title="Item">NavItem 2 content</NavItem>
                            <NavItem eventKey={3} title="Item">NavItem 3 content</NavItem>
                        </Nav>
                    </Panel>
                    <Panel header="Cource" eventKey="3">
                        blabla
                    </Panel>
                </Accordion>
            </div>
            <div className={styles.bslPageContent}>bsl-page-content</div>
        </div>;
    }

});