import React from 'react';
import ContextMenu from '../../components/ContextMenu';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)
    }

    static getDefault() {
        return {
            padding: 0,
            w: 1,
            h: 2,
            x: 0,
            y: Infinity,
            static: false,
            isDraggable: true,
            isResizable: true
        };
    }

    render() {
        return (
            <div style={this.props.style}>
                <ContextMenu {...this.props} />
                Frame {this.props.idx}
            </div>
        )
    }
}