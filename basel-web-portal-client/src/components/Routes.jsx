import React from 'react';
import {Router, Route, IndexRoute} from 'react-router';
import App from './App';
import HomePage from './home/HomePage';
import MonitoringPage from './monitoring/MonitoringPage';
import SecurityRecalculatePage from './monitoring/SecurityRecalculatePage';

export default <Route path="/" component={App}>
    <IndexRoute component={HomePage}/>
    <Route path="/monitoring" component={MonitoringPage} />
    <Route path="/security-recalculate" component={SecurityRecalculatePage} />
</Route>;