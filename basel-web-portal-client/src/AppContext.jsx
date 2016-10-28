import {observableFromStore} from './reduxStoreObserver';

export default AppContext = {

    store: null,
    observableState: null,

    getObservableState: function() {
        return this.observableState;
    },

    getStore: function() {
        return this.store;
    },

    setStore: function( createdStore ) {
        this.store = createdStore;

        this.observableState = observableFromStore( this.store );
    }

}

