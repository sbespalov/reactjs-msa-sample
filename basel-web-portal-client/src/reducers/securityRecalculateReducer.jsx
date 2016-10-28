import {List, Map} from 'immutable';


export default function securityRecalculateReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST':
            return state.setIn( ['recalculationResultList', 'loading'], true );
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE':
            return state.setIn( ['recalculationResultList', 'loading'], false )
                .setIn( ['recalculationResultList', 'data'], action.recalculationResultList );
        default:
            return state;
    }
}