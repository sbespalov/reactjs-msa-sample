import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import {PageHeader, Table, Pagination, Glyphicon, Button, Modal} from 'react-bootstrap';
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
        $( this.getRecalculateResultTableElement() ).loading( { zIndex: 5 });
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading( 'start' );
        }
        this.props.remoteFindRecalculationResultList( {});
    }

    componentWillUnmount() {
        console.log( '#UnMount' );
        $( this.getRecalculateResultTableElement() ).loading( 'stop' );
        $( this.getRecalculateResultTableElement() ).loading( 'destroy' );
    }

    componentDidUpdate() {
        console.log( '#Update' );
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading( 'start' );
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
        return ( this.props.totalCount || 0 ) / ( this.props.pageSize || 1 );
    }

    handlePageSelect( eventKey ) {
        this.props.remoteFindRecalculationResultList( {
            pageRequest: {
                pageNumber: eventKey
            }

        });
        return;
    }
    
    handleCloseFilterSettings(){
        this.props.applyFindRecalculationResultListFilter();
    }

    render() {
        return <div>
            <PageHeader>Security Recalculate
                <Button bsStyle="link" onClick={this.props.showFindRecalculationResultListFilter}>
                    <Glyphicon glyph="search"/>
                </Button>
            </PageHeader>
            <div ref="recalculateResultTableContainer">
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
                        onSelect={((that)=>{return (eventKey)=>{that.handlePageSelect( eventKey )}})(this)}/>
                </div>
            </div>

            <Modal show={this.props.showFilterSettings} onHide={((that)=>{return (eventKey)=>{this.handleCloseFilterSettings()}})(this)}>
                <Modal.Header closeButton>
                    <Modal.Title>Filter</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h4>Text in a modal</h4>
                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula.</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={((that)=>{return (eventKey)=>{this.handleCloseFilterSettings()}})(this)}>Close</Button>
                </Modal.Footer>
            </Modal>

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
        loading: state.getIn( ['recalculationResultList', 'loading'] ),
        showFilterSettings: state.get( 'showFilterSettings' )
    };
}

export const SecurityRecalculatePage = connect(
    mapStateToProps,
    actionCreators
)( SecurityRecalculatePageComponent );
