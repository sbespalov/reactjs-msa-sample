import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import {PageHeader, Table, Pagination, Glyphicon, Button, Modal, Form, FormGroup, Col, ControlLabel, FormControl, InputGroup} from 'react-bootstrap';
import "jquery-loading/jquery.loading";
import "jquery-loading/jquery.loading.css";
import "bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js";
import "bootstrap-datetimepicker/css/bootstrap-datetimepicker.css";
//import "eonasdan-bootstrap-datetimepicker";
import {actionCreators} from 'reducers/securityRecalculateHelper';
import RecalculationFilter from 'components/recalculation/RecalculationFilter';
import {observableFromStore} from 'reduxStoreObserver';

class SecurityRecalculateViewComponent extends React.Component {

    constructor( props, context ) {
        super( props, context );
        console.log( '#Constructor' );
        this.handlePageSelect = this.handlePageSelect.bind( this );
        this.handleShowFilterSettings = this.handleShowFilterSettings.bind( this );
    }

    componentDidMount() {
        console.log( '#Mount' );
        $( this.getRecalculateResultTableElement() ).loading( { zIndex: 5 });
        if ( this.getIsLoading() ) {
            $( this.getRecalculateResultTableElement() ).loading( 'start' );
        } else if ( this.getIsFailed() ) {
            $( this.getRecalculateResultTableElement() ).loading( {
                message: 'Failed to load...'
            });
        }
        this.props.remoteFindRecalculationResultList( this.props.filterValue );
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
        } else if ( this.getIsFailed() ) {
            $( this.getRecalculateResultTableElement() ).loading( 'stop' );
            $( this.getRecalculateResultTableElement() ).loading( 'destroy' );
            $( this.getRecalculateResultTableElement() ).loading( {
                message: 'Failed to load...'
            });
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

    getIsFailed() {
        return this.props.failed || false;
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

    handleCloseFilterSettings( filterValue ) {
        this.props.applyFindRecalculationResultListFilter( filterValue );
    }

    handleShowFilterSettings(){
        this.refs.resultListFilterDetailForm.setValue( this.props.filterValue );        
    }
    
    render() {
        var resultListFilterDetailForm = <RecalculationFilter ref="resultListFilterDetailForm"/>;
    
        return <div>
            <PageHeader>Security Recalculate
                <Button bsStyle="link" onClick={this.props.toggleFindRecalculationResultListFilter}>
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
                        bsSize="mdall"
                        prev
                        next
                        first
                        last
                        ellipsis
                        boundaryLinks
                        maxButtons={5}
                        items={this.getPageCount() }
                        activePage={this.getCurrentPage() }
                        onSelect={this.handlePageSelect}/>
                </div>
            </div>

            <Modal show={this.props.showFilterSettings}
                onEntered={this.handleShowFilterSettings}>
                <Modal.Header closeButton>
                    <Modal.Title>Filter</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {resultListFilterDetailForm}
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={( eventKey ) => { this.handleCloseFilterSettings( this.refs.resultListFilterDetailForm.getValue() ) } }>Ok</Button>
                    <Button onClick={( eventKey ) => { this.handleCloseFilterSettings() } }>Cancel</Button>
                </Modal.Footer>
            </Modal>

        </div>
    }

}

function mapStateToProps( state ) {
    state = state.securityRecalculate;
    return {
        data: state.getIn( ['recalculationResultList', 'data'] ),
        pageSize: state.getIn( ['recalculationResultList', 'pageSize'] ),
        pageNumber: state.getIn( ['recalculationResultList', 'pageNumber'] ),
        totalCount: state.getIn( ['recalculationResultList', 'totalCount'] ),
        loading: state.getIn( ['recalculationResultList', 'loading'] ),
        failed: state.getIn( ['recalculationResultList', 'failed'] ),
        showFilterSettings: state.get( 'showFilterSettings' ),
        filterValue: state.getIn( ['recalculationResultList', 'filter'] ).toJS()
    };
}

export const SecurityRecalculateView = connect(
    mapStateToProps,
    actionCreators
)( SecurityRecalculateViewComponent );
