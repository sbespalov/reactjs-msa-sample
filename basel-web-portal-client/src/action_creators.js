export function setState(state) {
  return {
    type: 'SET_STATE',
    state
  };
}


export function setActiveMenuItemKey(activeMenuItemKey){
    return {
        type: 'SET_ACTIVE_MAIN_MENU_ITEM',
        activeMenuItemKey
    }
}