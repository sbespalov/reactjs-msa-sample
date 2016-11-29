import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux'
import navigationReducer from 'reducers/navigationReducer';
import {securityRecalculateReducer} from 'reducers/securityRecalculateHelper';
import {securityReducer} from 'reducers/loginHelper';

export default combineReducers( {
    navigation: navigationReducer,
    securityRecalculate: securityRecalculateReducer,
    security: securityReducer,
    routing: routerReducer
});