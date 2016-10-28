import React from 'react';
import {Router, Route, IndexRoute} from 'react-router';
import App from 'components/App';
import * as page from 'components/pages/index';

var bbb = page.HomePage;

export default <Route path="/" component={App}>
    <IndexRoute component={page.HomePage}/>
    <Route path="/monitoring" component={page.MonitoringPage} />
    <Route path="/security-recalculate" component={page.SecurityRecalculatePage} />
    <Route path="/reports" component={page.ReportsPage} />
    <Route path="/referencies" component={page.ReferenciesPage} />
</Route>;