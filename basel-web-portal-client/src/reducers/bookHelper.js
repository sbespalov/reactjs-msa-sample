import {List, Map} from 'immutable';
import Immutable from 'immutable';
import AppContext from 'AppContext';

export const actionCreators = {

    remoteFindList: function( request ) {
        return function( dispatch, getState, {$}) {
            if ( getState().securityRecalculate.getIn( ['bookList', 'loading'] ) ) {
                return;
            }
            console.log( 'Request' );
            dispatch( {
                type: 'REMOTE_FIND_LIST_REQUEST',
                request: Immutable.fromJS(request)
            });
            $.ajax( {
                url: "/book/find?" + $.param(request), 
                type: 'GET',
                contentType: 'application/json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader ("Authorization", "Bearer " + AppContext.getUser().jwt);
                }                                
            }).done( function( responseData, status ) {
                console.log( 'Response' );
                dispatch( {
                    type: 'REMOTE_FIND_LIST_RESPONSE',
                    result: Immutable.fromJS( {
                        loading: false,
                        failed: false,
                        data: responseData.bookList,
                        pageNumber: responseData.pageResponse.pageNumber,
                        pageSize: responseData.pageResponse.pageSize,
                        totalCount: responseData.pageResponse.totalCount
                    })
                });
            }).fail((xhr)=>{
                console.log( 'Fail');
                dispatch( {
                    type: 'REMOTE_FIND_LIST_RESPONSE',
                    result: Immutable.fromJS( {
                        loading: false,
                        failed: true,
                        data: {},
                        pageNumber: 0,
                        pageSize: 0,
                        totalCount: 0
                    })
                });
            });;
        };
    },
    
    applyFindFilter(filter){
        return function( dispatch, getState, {$}) {
            if (filter){
                dispatch(actionCreators.remoteFindList(filter));
            }
            dispatch({
                type: 'TOGGLE_FIND_LIST_FILTER',
                result: false
            });
        }
    },
    
    toggleFindFilter(){
        return {
            type: 'TOGGLE_FIND_LIST_FILTER',
            result: true
        }
    }

}

export function bookReducer( state = Map(), action ) {
    switch ( action.type ) {
        case 'REMOTE_FIND_LIST_REQUEST':
            return state.setIn( ['bookList', 'loading'], true ).setIn(['bookList', 'filter'], action.request||{});
        case 'REMOTE_FIND_LIST_RESPONSE':
            return state.set('bookList', state.get('bookList').merge(action.result));
        case 'TOGGLE_FIND_LIST_FILTER':
            return state.set('showFilterSettings', action.result );
        default:
            return state;
    }
}