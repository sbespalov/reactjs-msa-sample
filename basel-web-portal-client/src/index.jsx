import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import reducer from './reducer';
import routes from './components/Routes'
import styles from '../lib/bootstrap-3.3.7/css/bootstrap.css';

const store = createStore( reducer );

store.dispatch( {
    type: 'SET_STATE',
    state: {
        navItems:
        [
            {
                id: '1',
                title: 'Monitoring',
                location: '/monitoring',
                items: [
                    {
                        id: '1.1',
                        title: 'Security recalculate',
                        location: '/security-recalculate'
                    },
                    {
                        id: '1.2',
                        title: 'Sub Item 2',
                        location: '/si2'
                    },
                    {
                        id: '1.3',
                        title: 'Sub Item 3',
                        location: '/si3'
                    }]
            },
            {
                id: '2',
                title: 'Report',
                location: '/reports'
            }, {
                id: '4',
                title: 'Item 4',
                location: '/monitoring',
                items: [
                    {
                        id: '4.1',
                        title: 'Sub Item 1',
                        location: '/si1'
                    },
                    {
                        id: '4.2',
                        title: 'Sub Item 2',
                        location: '/si2'
                    },
                    {
                        id: '4.3',
                        title: 'Sub Item 3',
                        location: '/si3'
                    }]
            },
            {
                id: '4',
                title: 'References',
                location: '/referencies'
            }]
        ,
        pages: {
            securityRecalculatePage: {
                recalculateResultData: [
                    {
                        date: new Date(),
                        security: 'Security',
                        bcd: 'Bid Calculate Detail',
                        acd: 'Ask Calculate Detail'
                    },
                    {
                        date: new Date(),
                        security: 'Security',
                        bcd: 'Bid Calculate Detail',
                        acd: 'Ask Calculate Detail'
                    }, {
                        date: new Date(),
                        security: 'Security',
                        bcd: 'Bid Calculate Detail',
                        acd: 'Ask Calculate Detail'
                    }, {
                        date: new Date(),
                        security: 'Security',
                        bcd: 'Bid Calculate Detail',
                        acd: 'Ask Calculate Detail'
                    }, {
                        date: new Date(),
                        security: 'Security',
                        bcd: 'Bid Calculate Detail',
                        acd: 'Ask Calculate Detail'
                    }]
            }
        }
    }
});


ReactDOM.render(
    <Provider store={store}>
        <Router history={hashHistory}>{routes}</Router>
    </Provider>,
    document.getElementById( 'app' )
);

//<NavDropdown
//eventKey={'1'}
//title="Monitoring"
//id="basic-nav-dropdown"
//role="menuitem"
//open={this.getActiveMenuItemKey().startsWith( '1' ) }
//ref={( targetComponent ) => { targetComponent && componentInstance.addOnClickNavigation( targetComponent, '/monitoring' ) } }>
//<MenuItem eventKey={'1.1'} href="#/security-recalculate">
//Security recalculate
//</MenuItem>
//</NavDropdown>
//<NavItem eventKey={'2'} href="#/reports">Reports</NavItem>
//<NavItem eventKey={'3'} href="#/referencies">References</NavItem>
