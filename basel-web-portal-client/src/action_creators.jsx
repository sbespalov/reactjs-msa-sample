import Immutable from 'immutable';
import AppContext from './AppContext';

export function setActiveMenuItemKey(activeMenuItemKey){
    return {
        type: 'SET_ACTIVE_MAIN_MENU_ITEM',
        activeMenuItemKey
    }
}