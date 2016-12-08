import {List, Map} from 'immutable';

export default function navigationReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'SET_ACTIVE_MAIN_MENU_ITEM':
            return state.set( 'activeMenuItemKey', action.key );
        default:
            return state;
    }
};