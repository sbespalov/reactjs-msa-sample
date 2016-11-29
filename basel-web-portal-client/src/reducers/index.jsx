import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux'
import navigationReducer from 'reducers/navigationReducer';
import {securityRecalculateReducer} from 'components/pages/logic/securityRecalculateHelper';
import {securityReducer} from 'components/pages/logic/loginHelper';

export default combineReducers( {
    navigation: navigationReducer,
    securityRecalculate: securityRecalculateReducer,
    security: securityReducer,
    routing: routerReducer
});