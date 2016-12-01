import React, { Component } from 'react';

export default class Jmodal extends Component {

	constructor(props) {
		super(props); 
	}

	state = {
		modal: null
	}

	componentDidMount() {
		let options = {
			afterClose: this.props.modalClose,
			...this.props.options
		};


		this.setState({ 
			modal: $('#' + this.props.id).jmodal(options)
		});
	}

	render() {

		if (this.props.open) {
			let modal = this.state.modal;
			modal.open();
		}
		
		return (
			<div id={this.props.id}>
				div({this.props.children})
			</div>
		)
	}

}