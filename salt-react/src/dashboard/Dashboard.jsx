import React from 'react';
import { render } from 'react-dom';
import PortletController from './components/PortletController';

class Dashboard {
	static main() {

		render(
			<PortletController />,
			document.getElementById('app')
		);
	}
}

Dashboard.main();