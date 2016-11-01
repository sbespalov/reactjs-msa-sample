import Immutable from 'immutable';
import navItems from 'components/navigation/navItems';

export default {
    navigation: Immutable.fromJS( {
        navItems: navItems
    }),
    securityRecalculate: Immutable.fromJS( {
        recalculationResultList: {
            data: [],
            pageCount: 0,
            currentPage: 0,
            loading: false
        }
    })
};

