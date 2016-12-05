import React from 'react';
import Draggable from 'react-draggable';

export default class PortletFrom extends React.Component {

	constructor(props) {
		super(props);
	}



	render() {
		return (
			<Draggable>
				<div className="panel panel-default">
					<div className="panel-body">
					</div>
				</div>
			</Draggable>
		);
	}
}
