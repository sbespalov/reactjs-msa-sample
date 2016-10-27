import {List, Map} from 'immutable';


export default function securityRecalculateReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST':
            return state.setIn( ['securityRecalculate', 'recalculationResultList', 'loading'], true );
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE':
            return state.setIn( ['securityRecalculate', 'recalculationResultList', 'loading'], false )
                .setIn( ['securityRecalculate', 'recalculationResultList', 'data'], action.recalculationResultList );
        default:
            return state;
    }
}