import React, { Component } from 'react';
import Modal from 'react-modal';

export default class Modals extends Component {

	constructor(props) {
		super(props);
	}

	render() {
        
		return (
			<Modal {...this.props}>
                {this.props.children}
            </Modal>
		)
	}

}