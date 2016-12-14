import React from 'react';
import Modal from 'react-modal';

class CreatePortletComponent extends React.Component {
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
        let { portletComponent, padding, ...props } = this.props;
        let style = { width: '100%', height: '100%', padding: padding };

        return (
            <div style={{ width: '100%', height: '100%', borderStyle: 'dashed', borderWidth: 1 }}
                onMouseOver={this.contextMenuShow}
                onMouseOut={this.contextMenuHidden}>
                <this.props.portletComponent {...props} style={style} isContextMenuShow={this.state.isContextMenuShow} />
            </div>
        )
    }
}