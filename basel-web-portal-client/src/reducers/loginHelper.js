import {List, Map} from 'immutable';
import Immutable from 'immutable';
import { hashHistory } from 'react-router';

export const actionCreators = {

    logout: function(){
        return {
            type: 'LOGOUT'
        }
    },
        
    remoteAuthentucate: function( userName, password ) {
        return function( dispatch, getState, {$}) {
            if ( getState().security.getIn( ['login', 'loading'] ) ) {
                return;
            }
            console.log( 'Request' );
            dispatch( {
                type: 'REMOTE_AUTHENTICATE_REQUEST'
            });
            if (userName === 'user@unknown.ru'){
                console.log( 'Sucess' );
                dispatch( {
                    type: 'REMOTE_AUTHENTICATE_RESPONSE',
                    result: Immutable.fromJS({
                            user: {firstName: 'User', lastName: 'User'},
                            login: {
                                loading: false
                            }
                        })
                    });
                hashHistory.push('/');
            } else {
                console.log( 'Fail');
                dispatch( {
                    type: 'REMOTE_AUTHENTICATE_RESPONSE',
                    result: Immutable.fromJS({
                            user: undefined,
                            login: {
                                message:   'Email or password is invalid.',
                                failed:         true,
                                loading:        false
                            }
                        })
                    });
            }
        };
    }

}

export function securityReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'REMOTE_AUTHENTICATE_REQUEST':
            return state.set( 'loading', true );
        case 'REMOTE_AUTHENTICATE_RESPONSE':
            return action.result;
        case 'LOGOUT':
            return Map();            
        default:
            return state;
    }
}