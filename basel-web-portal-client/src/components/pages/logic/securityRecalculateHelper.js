import {List, Map} from 'immutable';
import Immutable from 'immutable';

export const actionCreators = {

    remoteFindRecalculationResultList: function( request ) {
        return function( dispatch, getState, {$}) {
            if ( getState().securityRecalculate.getIn( ['recalculationResultList', 'loading'] ) ) {
                return;
            }
            console.log( 'Request' );
            dispatch( {
                type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST',
                request
            });
            $.ajax( {
                url: "http://localhost:8085/recalculation/findRecalculationResultList",
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify( request ),
                dataType: 'json'
            }).done( function( responseData, status ) {
                console.log( 'Response' );
                dispatch( {
                    type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE',
                    result: Immutable.fromJS( {
                        loading: false,
                        data: responseData.recalculationResultList,
                        pageNumber: responseData.pageResponse.pageNumber,
                        pageSize: responseData.pageResponse.pageSize,
                        totalCount: responseData.pageResponse.totalCount
                    })
                });
            });
        };
    },

}

export function securityRecalculateReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST':
            return state.setIn( ['recalculationResultList', 'loading'], true );
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE':
            return state.set('recalculationResultList', action.result );
        default:
            return state;
    }
}