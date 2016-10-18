import React from 'react';
import {connect} from 'react-redux';
import {PageHeader, Table} from 'react-bootstrap';
import * as actionCreators from '../../action_creators';

const SecurityRecalculatePage = React.createClass( {

    getRecalculateResultData: function() {
        return this.props.recalculateResultData || [];
    },

    render: function() {
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
                                <td>{row.get('date').toString()}</td>
                                <td>{row.get('security')}</td>
                                <td>{row.get('bcd')}</td>
                                <td>{row.get('acd')}</td>
                            </tr>;
                        })
                    }
                </tbody>
            </Table>
        </div>;
    }

});

function mapStateToProps( state ) {
    return {
        recalculateResultData: state.getIn( ['pages', 'securityRecalculatePage', 'recalculateResultData'] )
    };
}

export default connect(
    mapStateToProps,
    actionCreators
)( SecurityRecalculatePage );
