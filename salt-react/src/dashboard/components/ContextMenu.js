import React from 'react';
import Modal from 'react-modal';

import PortletUpdate from './PortletUpdate';

export default class ContextMenu extends React.Component {
    constructor(props) {
        super(props)

        this.onModalOpen = this.onModalOpen.bind(this);
        this.onModalClose = this.onModalClose.bind(this);

        this.onUpdatePortlet = this.onUpdatePortlet.bind(this);
        this.onClonePortlet = this.onClonePortlet.bind(this);
        this.onDeletePortlet = this.onDeletePortlet.bind(this);
    }

    state = {
        modalOpen: false
    }

    onModalOpen() {
        this.setState({ modalOpen: true });
    }

    onModalClose() {
        this.setState({ modalOpen: false });
    }

    onUpdatePortlet(portlet) {
        this.props.updatePortlet(portlet);
        this.onModalClose();
    }

    onClonePortlet() {
        this.props.clonePortlet(this.props.idx);
    }

    onDeletePortlet() {
        this.props.deletePortlet(this.props.idx);
    }

    render() {
        
        return (
            <div className="btn-toolbar" role="toolbar" style={{ position: 'absolute', display: this.props.isContextMenuShow ? '' : 'none' }}>
                <button type="button" className="btn btn-default draggable-point">
                    <i className="fa fa-arrows" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default" onClick={this.onModalOpen}>
                    <i className="fa fa-crop" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default"  onClick={this.onClonePortlet}>
                    <i className="fa fa-clone" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default" onClick={this.onDeletePortlet}>
                    <i className="fa fa-times" aria-hidden="true"></i>
                </button>
                {this.props.children}

                
                <Modal 
                    isOpen={this.state.modalOpen} 
                    onRequestClose={this.onModalClose} 
                    shouldCloseOnOverlayClick={true}>
                    <PortletUpdate 
                        portlet={this.props.portlet}
                        updatePortlet={this.onUpdatePortlet} />
                </Modal>
                
            </div>
        )
    }
}

ContextMenu.defaultProps = {
    isContextMenuShow: false
}