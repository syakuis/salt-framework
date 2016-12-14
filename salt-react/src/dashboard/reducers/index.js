import { combineReducers } from 'redux';
import update from 'react-addons-update';

import { ADD_PORTLET } from '../actions';

const defaultState = {
    layout: [],
    layouts: {},
    dashboard: {},
    portletCount: 0
}

const portlet = (state = defaultState, action) => {
    switch(action.type) {
        case ADD_PORTLET:
            let portletCount = state.portletCount + 1;
            let idx = 'idx_' + portletCount;

            return {
                portletCount: portletCount,
                dashboard: update(state.dashboard, {$merge: { [idx]: { ...action.portlet, idx: idx } }})
            }
        default: 
            return state;
    }
}

const portletReducer = combineReducers({ portlet })

export default portletReducer;