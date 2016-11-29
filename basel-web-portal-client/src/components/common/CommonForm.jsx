import React from 'react';
import {Form} from 'react-bootstrap';

class CommonForm extends Form {


    constructor( props, context ) {
        super( props, context );
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

export default CommonForm;