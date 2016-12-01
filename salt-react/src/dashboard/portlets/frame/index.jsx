import React from 'react';
//import Modal from 'react-modal';
import Modals from '../../components/Modals.jsx';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)

        this.evtMouseOver = this.evtMouseOver.bind(this);
        this.evtMouseOut = this.evtMouseOut.bind(this);
        this.evtClick = this.evtClick.bind(this);
        this.evtCopy = this.evtCopy.bind(this);
        this.onModalOpen = this.onModalOpen.bind(this);
        this.onModalClose = this.onModalClose.bind(this);

        this.onModalOpen2 = this.onModalOpen2.bind(this);
        this.onModalClose2 = this.onModalClose2.bind(this);

    }

    state = {
        isMouse: false,
        modal: {
            open: false,
            open2: false
        }
    }

    evtMouseOver(e) {
        this.setState({ isMouse: true });
    }

    evtMouseOut(e) {
        this.setState({ isMouse: false });
    }

    evtClick() {
        this.props.onDelPortlet(this.props.index)
    }

    evtCopy() {
        this.props.onCopyPortlet(this.props.index)
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
            <div style={{ width: '100%', height: '100%'}}>
                <div
                    style={{ width: '100%', height: '100%', backgroundColor: '#f4f4f4'}}
                    onMouseOver={this.evtMouseOver} onMouseOut={this.evtMouseOut}>
                    
                    <div className="btn-toolbar" role="toolbar" style={{ display: this.state.isMouse ? '' : 'none' }}>
                        <button type="button" className="btn btn-default draggable-point"><i className="fa fa-arrows" aria-hidden="true"></i></button>
                        <button type="button" className="btn btn-default" onClick={this.onModalOpen}>
                            <i className="fa fa-cog" aria-hidden="true"></i>
                        </button>
                        <button type="button" className="btn btn-default"  onClick={this.evtCopy}>
                            <i className="fa fa-clone" aria-hidden="true"></i>
                        </button>
                        <button type="button" className="btn btn-default" onClick={this.evtClick}>
                            <i className="fa fa-times" aria-hidden="true"></i>
                        </button>
                    </div>

                    Frame
                </div>

                
                <Modals 
                    isOpen={this.state.modal.open} 
                    onRequestClose={this.onModalClose} 
                    shouldCloseOnOverlayClick={true}>
                    <button onClick={this.onModalClose}>close</button>
                    <button onClick={this.onModalOpen2}>open</button>
                    qqqq

                    <Modals
                        isOpen={this.state.modal.open2} 
                        onRequestClose={this.onModalClose2} 
                        shouldCloseOnOverlayClick={true}>
                        <button onClick={this.onModalClose2}>close</button>
                        qqqqwqewqewq
                    </Modals>

                </Modals>
                
            </div>
        )
    }
} 