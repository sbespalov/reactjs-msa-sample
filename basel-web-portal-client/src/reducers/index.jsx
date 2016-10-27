import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux'
import navigationReducer from './navigationReducer';
import securityRecalculateReducer from './securityRecalculateReducer';

const reducer = combineReducers( {
    navigation: navigationReducer,
    securityRecalculate: securityRecalculateReducer,
    routing: routerReducer
});

export default reducer;