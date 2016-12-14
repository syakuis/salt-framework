export const ADD_PORTLET = 'ADD_PORTLET';
export const UPDATE_PORTLET = 'UPDATE_PORTLET';
export const REMOVE_PORTLET = 'REMOVE_PORTLET';
export const CLONE_PORTLET = 'CLONE_PORTLET';
export const UPDATE_LAYOUT = 'UPDATE_LAYOUT';

export const addPortlet = (portlet) => {
    return { type: ADD_PORTLET, portlet };
}
export const updatePortlet = (portlet) => {
    return { type: UPDATE_PORTLET, portlet };
}
export const removePortlet = (idx) => {
    return { type: REMOVE_PORTLET, idx };
}
export const clonePortlet = (idx) => {
    return { type: CLONE_PORTLET, idx };
}
export const updateLayout = (layout, layouts) => {
    return { type: UPDATE_LAYOUT, layout, layouts };
}