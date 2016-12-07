import {List, Map} from 'immutable';
import Immutable from 'immutable';
import AppContext from 'AppContext';

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
            console.log( 'Response' );
            dispatch( {
                type: 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE',
                result: Immutable.fromJS( {
                    loading: false,
                    failed: false,
                    data: [{
                                date: new Date(),
                                security: 'security',
                                bidCalculateResult: 'bidCalculateResult',
                                askCalculateDetail: 'askCalculateDetail'
                        },{
                            date: new Date(),
                            security: 'security',
                            bidCalculateResult: 'bidCalculateResult',
                            askCalculateDetail: 'askCalculateDetail'
                        },{
                            date: new Date(),
                            security: 'security',
                            bidCalculateResult: 'bidCalculateResult',
                            askCalculateDetail: 'askCalculateDetail'
                    }],
                    pageNumber: 1,
                    pageSize: 10,
                    totalCount: 100
                })
            });
        };
    },
    
    applyFindRecalculationResultListFilter(filter){
        return function( dispatch, getState, {$}) {
            if (filter){
                dispatch(actionCreators.remoteFindRecalculationResultList(filter));
            }
            dispatch({
                type: 'TOGGLE_FIND_RECALCULATION_RESULT_LIST_FILTER',
                result: false
            });
        }
    },
    
    toggleFindRecalculationResultListFilter(){
        return {
            type: 'TOGGLE_FIND_RECALCULATION_RESULT_LIST_FILTER',
            result: true
        }
    }

}

export function securityRecalculateReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_REQUEST':
            return state.setIn( ['recalculationResultList', 'loading'], true );
        case 'REMOTE_FIND_RECALCULATION_RESULT_LIST_RESPONSE':
            return state.set('recalculationResultList', action.result );
        case 'TOGGLE_FIND_RECALCULATION_RESULT_LIST_FILTER':
            return state.set('showFilterSettings', action.result );
        default:
            return state;
    }
}