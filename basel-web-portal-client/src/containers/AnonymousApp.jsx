import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import styles from './app.module.css';
import * as actionCreators from 'action_creators';

const AnonymousApp = React.createClass( {

    render: function() {

        var componentInstance = this;

        return <div className={styles.bslPageContainer}>
            {this.props.children}
        </div>;
    }

});

function mapStateToProps( state ) {
    var state = state.navigation;
    return {
        activeMenuItemKey: state.get( 'activeMenuItemKey' ),
        navItems: state.get( 'navItems' )
    };
}

export default connect(
    mapStateToProps,
    actionCreators
)( AnonymousApp );
