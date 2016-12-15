import React from 'react';
import Modal from 'react-modal';

import * as PortletComponents from '../portlets';

export default class CreatePortletComponent extends React.Component {
    constructor(props) {
        super(props)

        this.contextMenuShow = this.contextMenuShow.bind(this);
        this.contextMenuHidden = this.contextMenuHidden.bind(this);
    }

    state = {
        isContextMenuShow: false,
    }

    contextMenuShow() {
        this.setState({isContextMenuShow: true})
    }
    contextMenuHidden() {
        this.setState({isContextMenuShow: false})
    }

    render() {
        let style = { width: '100%', height: '100%', padding: this.props.padding };
        let component = PortletComponents[this.props.componentName];

        return (
            <div style={{ width: '100%', height: '100%', borderStyle: 'dashed', borderWidth: 1 }}
                onMouseOver={this.contextMenuShow}
                onMouseOut={this.contextMenuHidden}>
                <component style={style} isContextMenuShow={this.state.isContextMenuShow} />
            </div>
        )
    }
}