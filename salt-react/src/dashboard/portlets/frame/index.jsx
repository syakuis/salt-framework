import React from 'react';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)
        console.log(this.props)

        this.evtMouseOver = this.evtMouseOver.bind(this);
        this.evtMouseOut = this.evtMouseOut.bind(this);
    }

    state = {
        isMouse: false
    }

    evtMouseOver(e) {
        this.setState({ isMouse: true });
    }

    evtMouseOut(e) {
        this.setState({ isMouse: false });
    }

    render() {
        return (
            <div
                style={{ width: '100%', height: '100%', backgroundColor: '#f4f4f4'}}
                onMouseOver={this.evtMouseOver} onMouseOut={this.evtMouseOut}>
                
                <div className="btn-toolbar" role="toolbar" style={{ display: this.state.isMouse ? '' : 'none' }}>
                    <button type="button" className="btn btn-default draggable-point"><i className="fa fa-arrows" aria-hidden="true"></i></button>
                    <button type="button" className="btn btn-default"><i className="fa fa-cog" aria-hidden="true"></i></button>
                    <button type="button" className="btn btn-default"><i className="fa fa-clone" aria-hidden="true"></i></button>
                    <button type="button" className="btn btn-default" onClick={this.props.onDelPortlet(this.props.index)}>
                        <i className="fa fa-times" aria-hidden="true"></i>
                    </button>
                </div>


                Frame
            </div>
        )
    }
} 