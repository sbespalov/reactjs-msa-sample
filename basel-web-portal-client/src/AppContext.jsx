import {observableFromStore} from './reduxStoreObserver';

export default {

    store: null,
    observableState$: null,

    getObservableState: function() {
        return this.observableState;
    },

    getUser: function() {
        return this.user;
    },

    setUser: function( user ) {
        this.user = user;
    },

    init: function( createdStore ) {
        var that = this;

        this.store = createdStore;

        this.observableState$ = observableFromStore( this.store );

        this.observableState$
            .map( state => state.security )
            .distinctUntilChanged().subscribe(( val ) => {
                console.log( 'Auth changed' );
                if ( !val.toJS ) {
                    that.user = undefined;
                    return;
                }
                that.user = val.toJS().user;
            });

        var security = this.store.getState().security
        if ( !security || !security.get ) {
            return;
        }
        var user = this.store.getState().security.get( 'user' )
        this.setUser( user ? user.toJS() : undefined );
    }

}

