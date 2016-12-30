import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import {PageHeader, Table, Pagination, Glyphicon, Button, Modal, Form, FormGroup, Col, ControlLabel, FormControl, InputGroup} from 'react-bootstrap';
import "jquery-loading/jquery.loading";
import "jquery-loading/jquery.loading.css";
import "bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js";
import "bootstrap-datetimepicker/css/bootstrap-datetimepicker.css";
import {actionCreators} from 'reducers/bookHelper';
import BookFilter from 'components/brd/BookFilter';
import {observableFromStore} from 'reduxStoreObserver';

class BrdBookViewComponent extends React.Component {

    constructor( props, context ) {
        super( props, context );
        this.handlePageSelect = this.handlePageSelect.bind( this );
        this.handleShowFilterSettings = this.handleShowFilterSettings.bind( this );
    }

    componentDidMount() {
        $( this.getTableElement() ).loading( { zIndex: 5 });
        if ( this.getIsLoading() ) {
            $( this.getTableElement() ).loading( 'start' );
        } else if ( this.getIsFailed() ) {
            $( this.getTableElement() ).loading( {
                message: 'Failed to load...'
            });
        }
        this.props.remoteFindList( this.props.filterValue );
    }

    componentWillUnmount() {
        $( this.getTableElement() ).loading( 'stop' );
        $( this.getTableElement() ).loading( 'destroy' );
    }

    componentDidUpdate() {
        if ( this.getIsLoading() ) {
            $( this.getTableElement() ).loading( 'start' );
        } else if ( this.getIsFailed() ) {
            $( this.getTableElement() ).loading( 'stop' );
            $( this.getTableElement() ).loading( 'destroy' );
            $( this.getTableElement() ).loading( {
                message: 'Failed to load...'
            });
        } else {
            $( this.getTableElement() ).loading( 'stop' );
        }
    }

    getTableElement() {
        return ReactDOM.findDOMNode( this.refs.tableContainer );
    }

    getData() {
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
        this.props.remoteFindList( {
            pageRequest: {
                pageNumber: eventKey
            }

        });
        return;
    }

    handleCloseFilterSettings( filterValue ) {
        this.props.applyFindFilter( filterValue );
    }

    handleShowFilterSettings(){
        this.refs.filterDetailForm.setValue( this.props.filterValue );        
    }
    
    render() {
        var filterDetailForm = <BookFilter ref="filterDetailForm"/>;
    
        return <div>
            <PageHeader>Book
                <Button bsStyle="link" onClick={this.props.toggleFindFilter}>
                    <Glyphicon glyph="search"/>
                </Button>
            </PageHeader>
            <div ref="tableContainer">
                <Table responsive striped condensed hover>
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Source System</th>
                            <th>Book Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.getData().map(( row ) => {
                                return <tr>
                                    <td>{row.get( 'bookCode' ).toString() }</td>
                                    <td>{row.get( 'sourceSystem' ) }</td>
                                    <td>{row.get( 'bookType' ) }</td>
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
                    {filterDetailForm}
                </Modal.Body>
                <Modal.Footer>
                    <Button bsStyle="primary" onClick={( eventKey ) => { this.handleCloseFilterSettings( this.refs.filterDetailForm.getValue() ) } }>Ok</Button>
                    <Button onClick={( eventKey ) => { this.handleCloseFilterSettings() } }>Cancel</Button>
                </Modal.Footer>
            </Modal>

        </div>
    }

}

function mapStateToProps( state ) {
    state = state.book;
    return {
        data: state.getIn( ['bookList', 'data'] ),
        pageSize: state.getIn( ['bookList', 'pageSize'] ),
        pageNumber: state.getIn( ['bookList', 'pageNumber'] ),
        totalCount: state.getIn( ['bookList', 'totalCount'] ),
        loading: state.getIn( ['bookList', 'loading'] ),
        failed: state.getIn( ['bookList', 'failed'] ),
        showFilterSettings: state.get( 'showFilterSettings' ),
        filterValue: state.getIn( ['bookList', 'filter'] ).toJS()
    };
}

export const BrdBookView = connect(
    mapStateToProps,
    actionCreators
)( BrdBookViewComponent );
