import React from 'react';
import Modal from 'react-modal';

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
        let { portletComponent, ...props } = this.props;

        return (
            <div style={{ width: '100%', height: '100%', backgroundColor: '#f4f4f4'}}
                onMouseOver={this.contextMenuShow}
                onMouseOut={this.contextMenuHidden}>
                <this.props.portletComponent {...props} isContextMenuShow={this.state.isContextMenuShow} />
            </div>
        )
    }
}