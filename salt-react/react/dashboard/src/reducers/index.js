import { combineReducers } from 'redux';
import update from 'react-addons-update';
import _ from 'lodash';
import 'whatwg-fetch';

import * as actions from '../actions';
import * as portletComponents from '../portlets';

const defaultLayoutConfig = {
    className: "layout",
    autoSize: true,
    cols: {lg: 12, md: 10, sm: 6, xs: 4, xxs: 2},
    breakpoints: {lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0},
    //draggableCancel: '',
    draggableHandle: '.draggable-point',
    verticalCompact: true,
    //layout: [],
    //layouts: {},
    margin: [5,5],
    containerPadding: [0,0],
    rowHeight: 30,
    isDraggable: true,
    isResizable: true,
    useCSSTransforms: true
};

const defaultState = {
    portletComponents: portletComponents,
    layout: [],
    layouts: {},
    dashboard: {},
    portletCount: 0
};

const layoutConfig = (state = defaultLayoutConfig, action) => {
    switch(action.type) {
        case actions.SET_LAYOUT_CONFIG_MARGIN:
            return Object.assign({}, state, { margin: [action.x, action.y] });
        case actions.SET_LAYOUT_CONFIG_CONTAINER_PADDING:
            return Object.assign({}, state, { containerPadding: [action.x, action.y] });
        case actions.SET_LAYOUT_CONFIG_ROW_HEIGHT:
            return Object.assign({}, state, { rowHeight: action.height });
        default:
            return state;
    }
}

const portlet = (state = defaultState, action) => {
    let portletCount = state.portletCount + 1;
    let newIdx = 'idx_' + portletCount;

    switch(action.type) {
        case actions.INIT:
            let dashboard = {};
            let layout = [];
            let layouts = {};
console.log('181818188181');
            fetch('http://localhost:8080/dashboard/list', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }).then(function(res) {
                console.log(res);
            });

            return Object.assign({}, state, {
                portletCount: dashboard.length | 0,
                dashboard: dashboard,
                layout: layout,
                layouts: layouts
            });
        case actions.ADD_PORTLET:
            return Object.assign({}, state, {
                portletCount: portletCount,
                dashboard: update(state.dashboard, {$merge: { [newIdx]: { ...action.portlet, idx: newIdx } }})
            });

        case actions.UPDATE_PORTLET:
            let newPortlet = Object.assign({}, state.dashboard[action.portlet.idx], portlet);
            return Object.assign({}, state, {
                dashboard: update(state.dashboard, {$merge: { [action.portlet.idx]: newPortlet }})
            });

        case actions.DELETE_PORTLET:
            return Object.assign({}, state, { dashboard: _.omit(state.dashboard, [action.idx]) });

        case actions.CLONE_PORTLET:
            let portlet = Object.assign({}, { ...state.dashboard[action.idx], idx: newIdx });

            return Object.assign({}, state, {
                portletCount: portletCount, 
                dashboard: update(state.dashboard, {$merge: { [newIdx]: portlet }}) 
            });

        case actions.UPDATE_LAYOUT:
            return Object.assign({}, state, {
                layout: action.layout,
                layouts: action.layouts
            });
        default: 
            return state;
    }
}

const portletReducer = combineReducers({ layoutConfig, portlet })

export default portletReducer;