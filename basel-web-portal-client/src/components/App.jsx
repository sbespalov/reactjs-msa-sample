import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import styles from './app.module.css';
import SideBarMenu from './navigation/SideBarMenu';
import * as actionCreators from '../action_creators';

const App = React.createClass( {

    render: function() {

        var componentInstance = this;

        return <div className={styles.bslPageContainer}>
            <SideBarMenu activeMenuItemKey={this.props.activeMenuItemKey} onMenuItemSelect={this.props.setActiveMenuItemKey}/>
            <div className={styles.bslPageContent + ' ' + styles.main}>{this.props.children}</div>
        </div>;
    }

});

function mapStateToProps( state ) {
    return {
        activeMenuItemKey: state.get( 'activeMenuItemKey' )
    };
}

export default connect(
    mapStateToProps,
    actionCreators
)( App );
