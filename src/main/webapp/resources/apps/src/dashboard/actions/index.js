export const ADD_PORTLET = 'ADD_PORTLET';
export const UPDATE_PORTLET = 'UPDATE_PORTLET';
export const DELETE_PORTLET = 'DELETE_PORTLET';
export const CLONE_PORTLET = 'CLONE_PORTLET';
export const UPDATE_LAYOUT = 'UPDATE_LAYOUT';
export const SET_LAYOUT_CONFIG_MARGIN = 'SET_LAYOUT_CONFIG_MARGIN';
export const SET_LAYOUT_CONFIG_CONTAINER_PADDING = 'SET_LAYOUT_CONFIG_CONTAINER_PADDING';
export const SET_LAYOUT_CONFIG_ROW_HEIGHT = 'SET_LAYOUT_CONFIG_ROW_HEIGHT';

export const addPortlet = (portlet) => {
    return { type: ADD_PORTLET, portlet };
}
export const updatePortlet = (portlet) => {
    return { type: UPDATE_PORTLET, portlet };
}
export const deletePortlet = (idx) => {
    return { type: DELETE_PORTLET, idx };
}
export const clonePortlet = (idx) => {
    return { type: CLONE_PORTLET, idx };
}
export const updateLayout = (layout, layouts) => {
    return { type: UPDATE_LAYOUT, layout, layouts };
}
export const setLayoutConfigMargin = (x, y) => {
    return { type: SET_LAYOUT_CONFIG_MARGIN, x, y };
}
export const setLayoutConfigContainerPadding = (x, y) => {
    return { type: SET_LAYOUT_CONFIG_CONTAINER_PADDING, x, y };
}
export const setLayoutConfigRowHeight = (height) => {
    return { type: SET_LAYOUT_CONFIG_ROW_HEIGHT, height };
}