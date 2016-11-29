import React from 'react';
import {connect} from 'react-redux';
import { hashHistory } from 'react-router';

import {actionCreators} from 'reducers/loginHelper';
import AppContext from 'AppContext';

export const LogoutViewComponent = React.createClass( {

    componentWillMount: function() {
        this.props.logout();
        hashHistory.push( '/' );
    },

    render: function() {
        return null;
    }

});

export const LogoutView = connect(
    undefined,
    actionCreators
)( LogoutViewComponent );