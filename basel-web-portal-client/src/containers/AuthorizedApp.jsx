import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import styles from './app.module.css';
import SideBarMenu from 'components/navigation/SideBarMenu';
import * as actionCreators from 'action_creators';

const AuthorizedApp = React.createClass( {

    render: function() {
        return <div className={styles.bslPageContainer}>
            <SideBarMenu
                activeMenuItemKey={this.props.activeMenuItemKey}
                onMenuItemSelect={this.props.setActiveMenuItem}
                navItems={this.props.navItems}/>
            <div className={styles.bslPageContent + ' ' + styles.main}>{this.props.children}</div>
        </div>;
    }

});

function mapStateToProps( state ) {
    var state = state.navigation;
    return {
        activeMenuItemKey: state.get( 'activeMenuItemKey' ),
        navItems: state.get( 'navItems' ).toJS()
    };
}

export default connect(
    mapStateToProps,
    actionCreators
)( AuthorizedApp );
