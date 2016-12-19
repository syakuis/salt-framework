import React from 'react';
import { render } from 'react-dom';

import { Provider } from 'react-redux';
import { createStore } from 'redux';
import portletReducer from './reducers'

import PortletContainer from './containers/PortletContainer';

class Dashboard {
	static main() {

		let store = createStore(portletReducer);

		render(
			<Provider store = {store}>
				<PortletContainer />
			</Provider>,
			document.getElementById('app')
		);
	}
}

Dashboard.main();