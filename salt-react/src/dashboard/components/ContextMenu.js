import React from 'react';
import Modal from 'react-modal';

import { connect } from 'react-redux';
import { deletePortlet, clonePortlet } from '../actions';

import PortletUpdate from './PortletUpdate';

class ContextMenu extends React.Component {
    constructor(props) {
        super(props)

        this.onModalOpen = this.onModalOpen.bind(this);
        this.onModalClose = this.onModalClose.bind(this);
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

    render() {
        
        return (
            <div className="btn-toolbar" role="toolbar" style={{ position: 'absolute', display: this.props.isContextMenuShow ? '' : 'none' }}>
                <button type="button" className="btn btn-default draggable-point">
                    <i className="fa fa-arrows" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default" onClick={this.onModalOpen}>
                    <i className="fa fa-crop" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default"  onClick={() => this.props.clonePortlet(this.props.idx)}>
                    <i className="fa fa-clone" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default" onClick={() => this.props.deletePortlet(this.props.idx)}>
                    <i className="fa fa-times" aria-hidden="true"></i>
                </button>
                {this.props.children}

                
                <Modal 
                    contentLabel={this.props.idx}
                    isOpen={this.state.modalOpen} 
                    onRequestClose={this.onModalClose} 
                    shouldCloseOnOverlayClick={true}>
                    <PortletUpdate idx={this.props.idx} onModalClose={this.onModalClose}/>
                </Modal>
                
            </div>
        )
    }
}

ContextMenu.defaultProps = {
    isContextMenuShow: false
}


const mapDispatchToProps = (dispatch) => {
    return {
        deletePortlet: (idx) => dispatch(deletePortlet(idx)),
        clonePortlet: (idx) => dispatch(clonePortlet(idx))
    }
}

export default ContextMenu = connect(
  undefined,
  mapDispatchToProps
)(ContextMenu);