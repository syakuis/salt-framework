import React from 'react';
import * as PortletComponents from '../portlets';

export default class CreatePortletComponent extends React.Component {
    constructor(props) {
        super(props)

        this.contextMenuShow = this.contextMenuShow.bind(this);
        this.contextMenuHidden = this.contextMenuHidden.bind(this);
    }

    state = {
        isContextMenuShow: false,
        portletComponent: PortletComponents[this.props.componentName]
    }

    contextMenuShow() {
        this.setState({isContextMenuShow: true})
    }
    contextMenuHidden() {
        this.setState({isContextMenuShow: false})
    }

    render() {

        return (
            <div style={{ width: '100%', height: '100%', borderStyle: 'dashed', borderWidth: 1 }}
                onMouseOver={this.contextMenuShow}
                onMouseOut={this.contextMenuHidden}>
                <this.state.portletComponent idx={this.props.idx} padding={this.props.padding} isContextMenuShow={this.state.isContextMenuShow} />
            </div>
        )
    }
}