import styles from '../lib/bootstrap-3.3.7/css/bootstrap.css';
import appStyles from './styles/app.css';

import React from 'react';
import ReactDOM from 'react-dom';

import {createStore, applyMiddleware, combineReducers} from 'redux';
import {Provider} from 'react-redux';
import thunk from 'redux-thunk';

import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

import { Observable } from 'rxjs'

import observe from './reduxStoreObserver';

import $ from "jquery";

import preloadedState from './state.jsx'

import reducer from './reducers/index';
import remoteActionMiddleware from './remote_action_middleware';
import navItems from './components/navigation/navItems'
import routes from './components/Routes'

import * as actionCreators from './action_creators';

//const createStoreWithMiddleware = applyMiddleware(
//    thunk.withExtraArgument( { $ })
//)( createStore );

//const store = createStore(
//        rootReducer,
//        applyMiddleware(thunk)
//      );

//const store = createStoreWithMiddleware( combineReducers( { reducer, routerReducer }) );

const configureStore = function( store ) {
    observe( store,
        //if THIS changes, we the CALLBACK will be called
        state => state.routing.locationBeforeTransitions.pathname,
        ( store, previousValue, currentValue ) => console.log( 'Some property changed from ', previousValue, 'to', currentValue )
    );
};

const observableFromStore = function( store ) {
    return Observable.create( observer =>
        store.subscribe(() => observer.next( store.getState() ) )
    )
};

const store = createStore(
    reducer,
    preloadedState,
    applyMiddleware( thunk.withExtraArgument( { $ }) )
);

var state$ = observableFromStore(store);

const navChanged$ = state$
    .map(state => state.routing.locationBeforeTransitions.pathname )
    .distinctUntilChanged()
    .filter(pathname => pathname === '/security-recalculate');

navChanged$.subscribe( (val)=>{
    console.log( 'Some property changed ' + val);
    store.dispatch(actionCreators.remoteFindRecalculationResultList());
});

const history = syncHistoryWithStore( hashHistory, store )

//function mixStoreToRoutes( routes ) {
//    return routes && routes.map( route => ( {
//        ...route,
//        childRoutes: mixStoreToRoutes( route.childRoutes ),
//        onEnter: route.onEnter && function( props, replaceState, cb ) {
//            route.onEnter( store.dispatch, props )
//                .then(() => cb( null ) )
//                .catch( cb );
//        }
//    }));
//};

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>{routes}</Router>
    </Provider>,
    document.getElementById( 'app' )
);