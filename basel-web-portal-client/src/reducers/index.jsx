import {combineReducers} from 'redux';
import {routerReducer} from 'react-router-redux'
import navigationReducer from 'reducers/navigationReducer';
import securityRecalculateReducer from 'reducers/securityRecalculateReducer';

const reducer = combineReducers( {
    navigation: navigationReducer,
    securityRecalculate: securityRecalculateReducer,
    routing: routerReducer
});

export default reducer;