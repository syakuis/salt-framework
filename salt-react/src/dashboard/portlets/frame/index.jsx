import React from 'react';
import Modal from 'react-modal';
import ContextMenu from '../../components/ContextMenu.jsx';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)

        this.onModalOpen = this.onModalOpen.bind(this);
        this.onModalClose = this.onModalClose.bind(this);

        this.onModalOpen2 = this.onModalOpen2.bind(this);
        this.onModalClose2 = this.onModalClose2.bind(this);

    }

    state = {
        modal: {
            open: false,
            open2: false
        }
    }

    onModalOpen() {
        this.setState({modal: { ...this.state.modal, open: true }})
    }

    onModalClose() {
        this.setState({modal: { ...this.state.modal, open: false }})
    }

    onModalOpen2() {
        console.log('good');
        this.setState({modal: { ...this.state.modal, open2: true }})
    }

    onModalClose2() {
        console.log('good2');
        this.setState({modal: { ...this.state.modal, open2: false }})
    }


    render() {
        return (
            <div style={{ width: '90%', height: '90%', backgroundColor: 'rgb(119, 81, 81)'}}>

                <ContextMenu 
                    onClickConfig={this.onModalOpen}
                    onClickClone={() => this.props.onCopyPortlet(this.props.index)}
                    onClickRemove={() => this.props.onDelPortlet(this.props.index)}
                    isShow={this.props.isContextMenuShow} />
                
                Frame

                <Modal 
                isOpen={this.state.modal.open} 
                onRequestClose={this.onModalClose} 
                shouldCloseOnOverlayClick={true}>
                <button onClick={this.onModalClose}>close</button>
                <button onClick={this.onModalOpen2}>open</button>
                qqqq

                <Modal
                    isOpen={this.state.modal.open2} 
                    onRequestClose={this.onModalClose2} 
                    shouldCloseOnOverlayClick={true}>
                    <button onClick={this.onModalClose2}>close</button>
                    qqqqwqewqewq
                </Modal>

                </Modal>
            </div>
        )
    }
} 