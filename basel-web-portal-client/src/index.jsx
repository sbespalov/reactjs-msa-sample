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
        activeMenuItemKey: '1',
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