import React from 'react';
import ContextMenu from '../../components/ContextMenu';

export default class Frame extends React.Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div style={{ width: '100%', height: '100%', backgroundColor: 'rgb(119, 81, 81)', padding: this.props.padding }}>
                <ContextMenu {...this.props} />
                Frame {this.props.idx}
            </div>
        )
    }
} 