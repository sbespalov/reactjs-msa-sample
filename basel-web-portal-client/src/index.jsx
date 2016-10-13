import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import reducer from './reducer';
import routes from './components/Routes'

const store = createStore( reducer );

//const routes = <Route path="/" component={App}>
//<IndexRoute component={HomePage}/>
//<Route path="/monitoring" component={MonitoringPage} />
//</Route>;

ReactDOM.render(
    <Provider store={store}>
        <Router history={hashHistory}>{routes}</Router>
    </Provider>,
    document.getElementById( 'app' )
);