import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware} from 'redux';
import {Provider} from 'react-redux';
import thunk from 'redux-thunk';
import {Router, hashHistory} from 'react-router';
import { syncHistoryWithStore } from 'react-router-redux'
import { Observable } from 'rxjs'

import styles from 'bootstrap-3.3.7/css/bootstrap.css';
import appStyles from 'styles/app.css';
import appTheme from 'styles/theme.css';

import "moment";
import "string.prototype.startswith";
import $ from "jquery";

import getInitialState from 'initialState.jsx'
import reducers from 'reducers/index';
import routes from 'routes'
import remote_action_middleware from 'remote_action_middleware'
import AppContext from 'AppContext';

const observableFromStore = function( store ) {
    return Observable.create( observer =>
        store.subscribe(() => observer.next( store.getState() ) )
    )
};

const store = createStore(
    reducers,
    getInitialState(),
    applyMiddleware( remote_action_middleware, thunk.withExtraArgument( { $ }) )
);

var state$ = observableFromStore( store );

const authChanged$ = state$
    .map( state => state.security )
    .distinctUntilChanged();

authChanged$.subscribe(( val ) => {
    console.log( 'Auth changed' );
    if ( !val.toJS ) {
        console.log( 'Auth null: [' + val + "]" );
        return;
    }
    sessionStorage.setItem( 'security', JSON.stringify( val.toJS() ) );
});

const history = syncHistoryWithStore( hashHistory, store )

AppContext.init( store );

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>{routes}</Router>
    </Provider>,
    document.getElementById( 'app' )
);