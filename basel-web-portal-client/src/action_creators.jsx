import Immutable from 'immutable';
import AppContext from './AppContext';

export function setActiveMenuItemKey(activeMenuItemKey){
    return {
        type: 'SET_ACTIVE_MAIN_MENU_ITEM',
        activeMenuItemKey
    }
}

export function remoteFindRecalculationResultList(filter) {
    return function (dispatch, getState, {$}) {
        if (getState().securityRecalculate.getIn( ['recalculationResultList', 'loading'])){
            return;
        }
        console.log('Request');
        dispatch(remoteFindRecalculationResultListRequest(filter));
        $.get("http://localhost:8085/recalculation/findRecalculationResultList", function(data, status){
            console.log('Response');
            dispatch(remoteFindRecalculationResultListResponse(Immutable.fromJS(data.recalculationResultList)));
        });
    };
}

export function remoteFindRecalculationResultListRequest(filter) {
    return {
      type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST',
      filter
    };
}

export function remoteFindRecalculationResultListResponse(data) {
    return {
      type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE',
      recalculationResultList: data
    };
}