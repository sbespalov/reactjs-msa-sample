import {observableFromStore} from './reduxStoreObserver';

export default AppContext = {

    store: null,
    observableState: null,

    getObservableState: function() {
        return this.observableState;
    },

    getUser: function(){
        if (!this.store){
            return undefined;
        }
        var security = this.store.getState().security
        if (!security || !security.get){
            return undefined;
        }
        var user = this.store.getState().security.get('user')
        return user ? user.toJS() : undefined; 
    },
    
    getState: function() {
        return this.store.getState();
    },
    
    getStore: function() {
        return this.store;
    },

    setStore: function( createdStore ) {
        this.store = createdStore;

        this.observableState = observableFromStore( this.store );
    }

}

