import React from 'react';

export default class CreatePortlet extends React.Component {
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
        let { portlet, ...props } = this.props; 
        return (
            <div style={{ width: '100%', backgroundColor: '#f4f4f4'}}
                onMouseOver={this.contextMenuShow}
                onMouseOut={this.contextMenuHidden}>
                <portlet.component {...props} isContextMenuShow={this.state.isContextMenuShow}>
                    {this.props.portlet.body}
                </portlet.component>
            </div>
        )
    }
}