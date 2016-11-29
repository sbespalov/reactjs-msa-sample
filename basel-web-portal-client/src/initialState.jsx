import Immutable from 'immutable';
import navItems from 'components/navigation/navItems';

const getInitialState = function() {
    var result = {
        navigation: Immutable.fromJS( {
            navItems: navItems
        })
    };

    var security = sessionStorage.getItem( 'security' );
    if ( security ) {
        result['security'] = Immutable.fromJS( JSON.parse( security ) );
    }
    return result;
}

export default getInitialState;

