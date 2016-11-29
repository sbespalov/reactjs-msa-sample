import React from 'react';
import {PageHeader} from 'react-bootstrap';
import { hashHistory } from 'react-router';
import AppContext from 'AppContext';

export const HomeView = React.createClass( {

    componentWillMount: function() {
        var user = AppContext.getUser();
        user ? hashHistory.push( '/monitoring' ) : hashHistory.push( '/login' );
    },
    
    render: function() {
        return null;
    }

}); 