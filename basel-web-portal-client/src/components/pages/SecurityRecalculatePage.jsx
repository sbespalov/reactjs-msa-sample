import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import {PageHeader, Table, Pagination} from 'react-bootstrap';
import "jquery-loading/jquery.loading";
import "jquery-loading/jquery.loading.css";
import {actionCreators} from 'components/pages/logic/securityRecalculateHelper';
import {observableFromStore} from 'reduxStoreObserver';

class SecurityRecalculatePageComponent extends React.Component {

    constructor( props, context ) {
        super( props, context );
        console.log( '#Constructor' );
    }

    componentDidMount() {
        console.log( '#Mount' );
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading();
        }
        this.props.remoteFindRecalculationResultList();
    }

    componentWillUnmount() {
        console.log( '#UnMount' );
        $( this.getRecalculateResultTableElement() ).loading( 'stop' );
        $( this.getRecalculateResultTableElement() ).loading( 'destroy' );
    }

    componentDidUpdate() {
        console.log( '#Update' );
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading();
        } else {
            $( this.getRecalculateResultTableElement() ).loading( 'stop' );
        }
    }

    getRecalculateResultTableElement() {
        return ReactDOM.findDOMNode( this.refs.recalculateResultTableContainer );
    }

    getRecalculateResultData() {
        return this.props.data || [];
    }

    getIsLoading() {
        return this.props.loading || false;
    }

    getCurrentPage() {
        return this.props.pageNumber || 0;
    }

    getPageCount() {
        return (this.props.totalCount || 0)/(this.props.pageSize || 1);
    }

    handlePageSelect( eventKey ) {
        return;
    }

    render() {
        return <div>
            <PageHeader>Security Recalculate</PageHeader>
            <div ref="recalculateResultTableContainer" className="bslPagedTableContainer">
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
                <div className="bslPagedTableNavigation">
                    <Pagination
                        bsSize="small"
                        prev
                        next
                        first
                        last
                        ellipsis
                        boundaryLinks
                        maxButtons={5}
                        items={this.getPageCount() }
                        activePage={this.getCurrentPage() }
                        onSelect={this.handlePageSelect() }/>
                </div>
            </div>
        </div>
    }

}

SecurityRecalculatePageComponent.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps( state ) {
    state = state.securityRecalculate;
    return {
        data: state.getIn( ['recalculationResultList', 'data'] ),
        pageSize: state.getIn( ['recalculationResultList', 'pageSize'] ),
        pageNumber: state.getIn( ['recalculationResultList', 'pageNumber'] ),
        totalCount: state.getIn( ['recalculationResultList', 'totalCount'] ),
        loading: state.getIn( ['recalculationResultList', 'loading'] )
    };
}

export const SecurityRecalculatePage = connect(
    mapStateToProps,
    actionCreators
)( SecurityRecalculatePageComponent );
