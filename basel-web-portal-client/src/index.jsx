import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware, combineReducers} from 'redux';
import {Provider} from 'react-redux';
import thunk from 'redux-thunk';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'
import { Observable } from 'rxjs'
import $ from "jquery";
import preloadedState from 'initialState.jsx'

import styles from 'bootstrap-3.3.7/css/bootstrap.css';
import appStyles from 'styles/app.css';

import "moment";
import "string.prototype.startswith";

import observe from 'reduxStoreObserver';

import reducer from 'reducers/index';
import remoteActionMiddleware from 'remote_action_middleware';
import navItems from 'components/navigation/navItems'
import routes from 'components/Routes'
import remote_action_middleware from 'remote_action_middleware'
import * as actionCreators from 'action_creators';

import AppContext from 'AppContext';

const observableFromStore = function( store ) {
    return Observable.create( observer =>
        store.subscribe(() => observer.next( store.getState() ) )
    )
};

const store = createStore(
    reducer,
    preloadedState,
    applyMiddleware( remote_action_middleware, thunk.withExtraArgument( { $ }) )
);
AppContext.setStore(store);

//var state$ = observableFromStore(store);
//
//const navChanged$ = state$
//    .map(state => state.routing.locationBeforeTransitions.pathname )
//    .distinctUntilChanged()
//    .filter(pathname => pathname === '/security-recalculate');
//
//navChanged$.subscribe( (val)=>{
//    console.log( 'Property changed ' + val);
//    store.dispatch(actionCreators.remoteFindRecalculationResultList());
//});

const history = syncHistoryWithStore( hashHistory, store )

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>{routes}</Router>
    </Provider>,
    document.getElementById( 'app' )
);