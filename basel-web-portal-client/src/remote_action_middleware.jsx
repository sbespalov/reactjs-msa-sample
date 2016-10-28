export default store => next => action => {
    //console.log('In midleware');
    return next( action );
}