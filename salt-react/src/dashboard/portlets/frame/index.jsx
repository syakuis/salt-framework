import React from 'react';
import {DraggableCore} from 'react-draggable';
import ConfigModal from '../../components/ConfigModal.jsx';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)

        this.evtMouseOver = this.evtMouseOver.bind(this);
        this.evtMouseOut = this.evtMouseOut.bind(this);
        this.evtClick = this.evtClick.bind(this);
        this.evtCopy = this.evtCopy.bind(this);
        this.onOpen = this.onOpen.bind(this);

    }

    state = {
        isMouse: false,
        open: false
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

    onOpen(e) {
        this.setState({open: true})
    }

    render() {
        return (
            <div style={{ width: '100%', height: '100%'}}>
                <div
                    style={{ width: '100%', height: '100%', backgroundColor: '#f4f4f4'}}
                    onMouseOver={this.evtMouseOver} onMouseOut={this.evtMouseOut}>
                    
                    <div className="btn-toolbar" role="toolbar" style={{ display: this.state.isMouse ? '' : 'none' }}>
                        <button type="button" className="btn btn-default draggable-point"><i className="fa fa-arrows" aria-hidden="true"></i></button>
                        <button type="button" className="btn btn-default" onClick={this.onOpen}>
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

                <ConfigModal isOpen={this.state.open}>
                    <div>aa</div>
                </ConfigModal>
            </div>
        )
    }
} 