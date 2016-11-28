import React from 'react';
import { render } from 'react-dom';
import PortletController from './components/PortletController';

class Dashboard {
	static main() {
		let Dashboard = React.createClass({

			render() {

			}
		});

		render(
			<PortletController />,
			document.getElementById('app')
		)
	}
}

Dashboard.main();