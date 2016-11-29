import React from 'react';
import {Router, Route, IndexRoute} from 'react-router';
import Main from 'containers/Main';
import * as page from 'components/pages/index';

export default <Route path="/" component={Main}>
    <IndexRoute component={page.HomePage}/>" +
    <Route path="/login" component={page.LoginPage} />
    <Route path="/monitoring" component={page.MonitoringPage} />
    <Route path="/security-recalculate" component={page.SecurityRecalculatePage} />
    <Route path="/reports" component={page.ReportsPage} />
    <Route path="/referencies" component={page.ReferenciesPage} />
</Route>;