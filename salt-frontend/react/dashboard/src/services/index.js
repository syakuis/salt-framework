import axios from 'axios';

const host = '/dashboard';

const dashboardDataReNaming = (portlet) => {
	return {
		componentName: portlet.portletName,
		h: portlet.height,
		w: portlet.width,
		isDraggable: portlet.isDraggable,
		isResizable: portlet.isResizable,
		static: portlet.isStatic,
		padding: portlet.padding,
		x: portlet.coordX,
		y: portlet.coordY,
		idx: portlet.portletId
	};
}

const portletDataReNaming = (dashboard) => {
	return {
		portletName: dashboard.componentName,
		height: dashboard.h,
		width: dashboard.w,
		isDraggable: dashboard.isDraggable,
		isResizable: dashboard.isResizable,
		isStatic: dashboard.static,
		padding: dashboard.padding,
		coordX: dashboard.x,
		coordY: dashboard.y,
		portletId: dashboard.idx
	};
}

const saveDataConverter = (data) => {
	let portlets = [];

	_.forEach(data.dashboard, (value, key) => {
		portlets.push(portletDataReNaming(value));
	});

	return Object.assign({}, {
		dashboard: {
			marginX: data.layoutConfig.margin[0],
			marginY: data.layoutConfig.margin[1],
			paddingX: data.layoutConfig.containerPadding[0],
			paddingY: data.layoutConfig.containerPadding[1],
			height: data.layoutConfig.rowHeight
		},
		layout: data.layout,
		layouts: data.layouts,
		portlets: portlets
	});

}

export const getDashboard = () => {
	return axios.get(host + '/list');
}

export const saveDashboard = (data) => {
	return axios.post(host + '/save', saveDataConverter(data));
}