import React from 'react';
import ContextMenu from '../../components/ContextMenu';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)
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