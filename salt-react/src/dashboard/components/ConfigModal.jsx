import React, { Component } from 'react';

export default class ConfigModal extends Component {

	constructor(props) {
		super(props); 
	}

	state = {
		body: this.props.children,
		modal: null
	}

	componentDidMount() {
		this.setState({ 
			modal: $("#test").jmodal()
		});
	}

	render() {

		if (this.props.opend) {
			let modal = this.state.modal;
			modal.open();
		}
		

		return (
			<div id="test">
				<p>man</p>
				{this.state.body}
			</div>
		)
	}

}