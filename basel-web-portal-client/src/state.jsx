import Immutable from 'immutable';
import navItems from './components/navigation/navItems';

export default {
    navigation: Immutable.fromJS( {
        navItems: navItems
    }),
    securityRecalculate: Immutable.fromJS( {
        recalculationResultList: {
            data: [
                {
                    date: new Date(),
                    security: 'Security',
                    bcd: 'Bid Calculate Detail',
                    acd: 'Ask Calculate Detail'
                },
                {
                    date: new Date(),
                    security: 'Security',
                    bcd: 'Bid Calculate Detail',
                    acd: 'Ask Calculate Detail'
                }, {
                    date: new Date(),
                    security: 'Security',
                    bcd: 'Bid Calculate Detail',
                    acd: 'Ask Calculate Detail'
                }, {
                    date: new Date(),
                    security: 'Security',
                    bcd: 'Bid Calculate Detail',
                    acd: 'Ask Calculate Detail'
                }, {
                    date: new Date(),
                    security: 'Security',
                    bcd: 'Bid Calculate Detail',
                    acd: 'Ask Calculate Detail'
                }],
            loading: true
        }
    })
};

