import Immutable from 'immutable';
import AppContext from './AppContext';
import { hashHistory } from 'react-router'

export function setActiveMenuItem(key, location){
    hashHistory.push(location);
    
    return {
        type: 'SET_ACTIVE_MAIN_MENU_ITEM',
        key
    }
}