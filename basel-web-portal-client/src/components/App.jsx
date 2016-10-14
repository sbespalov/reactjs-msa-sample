import React from 'react';
import ReactDOM from 'react-dom';
import styles from './app.css';
import SideBarMenu from './navigation/SideBarMenu';

export default React.createClass( {

    render: function() {

        var componentInstance = this;

        return <div className={styles.bslPageContainer}>
            <SideBarMenu/>
            <div className={styles.bslPageContent + ' ' + styles.main}>{this.props.children}</div>
        </div>;
    }
});