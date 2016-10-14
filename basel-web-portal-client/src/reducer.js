import {List, Map} from 'immutable';

function setState(state, newState) {
    return state.merge(newState);
}


export default function(state = Map(), action) {
    switch (action.type) {
        case 'SET_STATE':
            return setState(state, action.state);
        case 'SET_ACTIVE_MAIN_MENU_ITEM':
            return state.set('activeMenuItemKey', action.activeMenuItemKey);
            
        default:
            return state;
    }
    return state;
}