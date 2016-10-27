import Immutable from 'immutable';

export function setActiveMenuItemKey(activeMenuItemKey){
    return {
        type: 'SET_ACTIVE_MAIN_MENU_ITEM',
        activeMenuItemKey
    }
}

export function remoteFindRecalculationResultList(filter) {
    return function (dispatch, getState, {$}) {
        dispatch(remoteFindRecalculationResultListRequest());
        $.get("http://localhost:8085/recalculation/findRecalculationResultList", function(data, status){
            dispatch(remoteFindRecalculationResultListResponse(Immutable.fromJS(data.recalculationResultList)));
        });
    };
}

export function remoteFindRecalculationResultListRequest() {
    return {
      type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST'
    };
}

export function remoteFindRecalculationResultListResponse(data) {
    return {
      type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE',
      recalculationResultList: data
    };
}
