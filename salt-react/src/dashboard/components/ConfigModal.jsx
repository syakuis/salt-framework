import React, { Component } from 'react';
import Modal from 'react-modal';

export default class ConfigModal extends Component {

	constructor(props) {
		super(props); 
	}

	render() {
		return (
			<Modal isOpen={this.props.isOpen} style={this.props.styles}>
				{this.props.children}
			</Modal>
		)
	}
}

ConfigModal.defaultProps = {
	styles: {
		overlay : {
			position          : 'fixed',
			top               : 0,
			left              : 0,
			right             : 0,
			bottom            : 0,
			backgroundColor   : 'rgba(255, 255, 255, 0.75)'
		},
		content : {
			position                   : 'absolute',
			top                        : '40px',
			left                       : '40px',
			right                      : '40px',
			bottom                     : '40px',
			border                     : '1px solid #ccc',
			background                 : '#fff',
			overflow                   : 'auto',
			WebkitOverflowScrolling    : 'touch',
			borderRadius               : '4px',
			outline                    : 'none',
			padding                    : '20px'

		}
	}
}