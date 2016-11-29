import React from 'react';
import * as rb from 'react-bootstrap';
import {connect} from 'react-redux';
import {actionCreators} from 'reducers/loginHelper';
import CommonForm from 'components/common/CommonForm';

import styles from './login-page.module.css';

class LoginViewComponent extends React.Component {

    handleLogin( data ) {
        this.props.remoteAuthentucate( data.email, data.password );
    }

    render() {

        var errorMessage = <rb.Alert bsStyle="danger">
            <p>{this.props.message}</p>
        </rb.Alert>;

        return <div>
            <rb.Panel header="Login" bsStyle="primary" className={styles.bslLoginPanel}>
                { this.props.failed ? errorMessage : null }
                <CommonForm horizontal ref="loginForm">
                    <rb.FormGroup>
                        <rb.Col componentClass={rb.ControlLabel} md={2}>
                            Email
                        </rb.Col>
                        <rb.Col md={10}>
                            <rb.FormControl type="text" placeholder="your@mail.com" id="email"/>
                        </rb.Col>
                    </rb.FormGroup>

                    <rb.FormGroup>
                        <rb.Col componentClass={rb.ControlLabel} md={2}>
                            Password
                        </rb.Col>
                        <rb.Col md={10}>
                            <rb.FormControl type="text" placeholder="password" id="password"/>
                        </rb.Col>
                    </rb.FormGroup>
                    <rb.FormGroup>
                        <rb.Col smOffset={2} sm={10} onClick={( eventKey ) => { this.handleLogin( this.refs.loginForm.getValue() ) } }>
                            <rb.Button>
                                Sign in
                            </rb.Button>
                        </rb.Col>
                    </rb.FormGroup>
                </CommonForm>
            </rb.Panel>
        </div>;
    }

}

function mapStateToProps( state ) {
    state = state.security;
    return {
        failed: state.getIn( ['login', 'failed'] ),
        message: state.getIn( ['login', 'message'] )
    };
}

export const LoginView = connect(
    mapStateToProps,
    actionCreators
)( LoginViewComponent );
