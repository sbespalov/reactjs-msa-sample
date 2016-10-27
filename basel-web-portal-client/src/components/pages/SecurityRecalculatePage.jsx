import React from 'react';
import {connect} from 'react-redux';
import {PageHeader, Table} from 'react-bootstrap';
import * as actionCreators from '../../action_creators';
import {observableFromStore} from '../../reduxStoreObserver';

const SecurityRecalculatePageComponent = React.createClass( {

    getRecalculateResultData: function() {
        return this.props.recalculateResultListData || [];
    },

    getIsLoading: function() {
        return this.props.loading || false;
    },

    render: function() {
        //this.props.remoteFindRecalculationResultList();
        if ( this.getIsLoading() ) {
            return <div>
                <PageHeader>Security Recalculate</PageHeader>
                <span className="glyphicon glyphicon-refresh glyphicon-spin"></span>
            </div>
        }
        return <div>
            <PageHeader>Security Recalculate</PageHeader>
            <Table responsive striped condensed hover>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Security</th>
                        <th>Bid Calculate Detail</th>
                        <th>Ask Calculate Detail</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        this.getRecalculateResultData().map(( row ) => {
                            return <tr>
                                <td>{row.get( 'date' ).toString() }</td>
                                <td>{row.get( 'security' ) }</td>
                                <td>{row.get( 'bidCalculateResult' ) }</td>
                                <td>{row.get( 'askCalculateDetail' ) }</td>
                            </tr>;
                        })
                    }
                </tbody>
            </Table>
        </div>;
    }

});

var navChanged$

function mapStateToProps( state ) {
    //    if ( !navChanged$ ) {
    //        navChanged$ = state$
    //            .map(state => state.routing.locationBeforeTransitions.pathname )
    //            .distinctUntilChanged()
    //            .filter(pathname => pathname === '/security-recalculate');
    //        
    //        navChanged$.subscribe( (val)=>{
    //            console.log( 'Some property changed ' + val);
    //            store.dispatch(actionCreators.remoteFindRecalculationResultList());
    //        });        
    //        
    //    }
    state = state.securityRecalculate;
    return {
        recalculateResultListData: state.getIn( ['securityRecalculate', 'recalculationResultList', 'data'] ),
        loading: state.getIn( ['securityRecalculate', 'recalculationResultList', 'loading'] )
    };
}

export const SecurityRecalculatePage = connect(
    mapStateToProps,
    actionCreators
)( SecurityRecalculatePageComponent );
