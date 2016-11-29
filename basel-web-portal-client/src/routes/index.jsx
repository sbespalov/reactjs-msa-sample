import React from 'react';
import {Router, Route, IndexRoute} from 'react-router';
import Main from 'containers/Main';
import * as page from 'views/index';

export default <Route path="/" component={Main}>
    <IndexRoute component={page.HomeView}/>" +
    <Route path="/login" component={page.LoginView} />
    <Route path="/logout" component={page.LogoutView} />
    <Route path="/monitoring" component={page.MonitoringView} />
    <Route path="/security-recalculate" component={page.SecurityRecalculateView} />
    <Route path="/reports" component={page.ReportsView} />
    <Route path="/referencies" component={page.ReferenciesView} />
</Route>;