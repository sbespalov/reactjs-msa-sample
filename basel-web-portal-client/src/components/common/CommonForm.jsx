import React from 'react';
import {Form} from 'react-bootstrap';

class CommonForm extends Form {


    constructor( props, context ) {
        super( props, context );
        this.visitReactElements = this.visitReactElements.bind( this );
    }

    visitReactElements( x, f, depth = 0 ) {
        var that = this;
        
        if ( !x || !x.props ) return;

        f( x, depth );
        
        React.Children.forEach( x.props.children, function( x ) {
            that.visitReactElements( x, f, depth + 1 );
        })
    }

    getValue() {
        var value = {};
        var element = ReactDOM.findDOMNode( this );
        var id = $(element).find('input').each((i,e)=>{
            value[e.id]=e.value;
        });

        return value;
    }

}

CommonForm.contextTypes = {
    refs: React.PropTypes.arrayOf( React.PropTypes.element )
};

export default CommonForm;