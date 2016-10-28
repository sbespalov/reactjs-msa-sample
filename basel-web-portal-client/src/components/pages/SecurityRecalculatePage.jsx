import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import {PageHeader, Table} from 'react-bootstrap';
import $ from 'jquery';
import "jquery-loading-1.2.0/jquery.loading";
import "jquery-loading-1.2.0/jquery.loading.css";
import * as actionCreators from 'action_creators';
import {observableFromStore} from 'reduxStoreObserver';

class SecurityRecalculatePageComponent extends React.Component {

    constructor( props, context ) {
        super( props, context );
        console.log('#Constructor');
    }

    componentDidMount() {
        console.log('#Mount');
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading();
        }
        this.props.remoteFindRecalculationResultList();
    }

    componentWillUnmount() {
        console.log('#UnMount');
        $( this.getRecalculateResultTableElement() ).loading( 'stop' );
    }

    componentDidUpdate() {
        console.log('#Update');
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading();
        } else {
            $( this.getRecalculateResultTableElement() ).loading( 'stop' );
        }
    }

    getRecalculateResultTableElement() {
        return ReactDOM.findDOMNode( this.refs.recalculateResultTable );
    }

    getRecalculateResultData() {
        return this.props.recalculateResultListData || [];
    }

    getIsLoading() {
        return this.props.loading || false;
    }

    render() {
        return <div>
            <PageHeader>Security Recalculate</PageHeader>
            <Table responsive striped condensed hover ref="recalculateResultTable">
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

}

SecurityRecalculatePageComponent.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps( state ) {
    state = state.securityRecalculate;
    return {
        recalculateResultListData: state.getIn( ['recalculationResultList', 'data'] ),
        loading: state.getIn( ['recalculationResultList', 'loading'] )
    };
}

export const SecurityRecalculatePage = connect(
    mapStateToProps,
    actionCreators
)( SecurityRecalculatePageComponent );
