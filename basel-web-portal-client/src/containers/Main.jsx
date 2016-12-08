import React from 'react';
import {connect} from 'react-redux';
import AuthorizedApp from 'containers/AuthorizedApp';
import AnonymousApp from 'containers/AnonymousApp';
import * as page from 'views/index';
import { hashHistory } from 'react-router';
import AppContext from 'AppContext';

class Main extends React.Component {

    render() {
        var user = AppContext.getUser();
        var children = this.props.children;

        if ( user && user.jwt ) {
            return <AuthorizedApp>{children}</AuthorizedApp>;
        }
        return <AnonymousApp>{children}</AnonymousApp>;
    }
}

export default Main;
