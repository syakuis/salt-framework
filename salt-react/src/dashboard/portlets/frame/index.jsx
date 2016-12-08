import React from 'react';
import Modal from 'react-modal';
import ContextMenu from '../../components/ContextMenu';
import PortletUpdate from '../../components/PortletUpdate';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)

        this.onModalOpen = this.onModalOpen.bind(this);
        this.onUpdatePortlet = this.onUpdatePortlet.bind(this);
    }

    state = {
        modalOpen: false
    }

    onModalOpen() {
        this.setState({ modalOpen: true });
    }

    onUpdatePortlet(portlet) {
        this.props.updatePortlet(portlet);
        this.setState({ modalOpen: false });
    }

    render() {
        return (
            <div style={{ width: '100%', height: '100%', backgroundColor: 'rgb(119, 81, 81)', padding: this.props.padding }}>

                <ContextMenu 
                    onClickConfig={this.onModalOpen}
                    onClickClone={() => this.props.clonePortlet(this.props.idx)}
                    onClickRemove={() => this.props.deletePortlet(this.props.idx)}
                    isShow={this.props.isContextMenuShow} />
                
                Frame {this.props.idx}

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