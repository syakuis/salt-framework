import React from 'react';
import { render } from 'react-dom';
import Grid from './components/Grid';

class Dashboard {
	static main() {
		let Dashboard = React.createClass({

			render() {

			}
		});

		render(
			<Grid />,
			document.getElementById('app')
		)
	}
}

Dashboard.main();