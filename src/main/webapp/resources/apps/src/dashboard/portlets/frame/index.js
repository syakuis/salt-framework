import React from 'react';
import ContextMenu from '../../components/ContextMenu';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)
    }

    state={
        iframeSrc: '',
    }

    static getDefault() {
        return {
            padding: 0,
            w: 5,
            h: 5,
            x: 0,
            y: Infinity,
            static: false,
            isDraggable: true,
            isResizable: true
        };
    }

    render() {
        return (
            <div style={{ width: '100%', height: '100%', padding: this.props.padding }}>
                <ContextMenu idx={this.props.idx} isContextMenuShow={this.props.isContextMenuShow}>
                    <button type="button" className="btn btn-default">
                        <i className="fa fa-cog" aria-hidden="true"></i>
                    </button>
                </ContextMenu>
                <iframe src="http://aintop.co.kr" width="100%" height="100%"
                 style={{ border: 'none', overflow: 'hidden' }}></iframe>
            </div>
        )
    }
}