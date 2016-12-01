import React from 'react';

export default class ContextMenu extends React.Component {
    constructor(props) {
        super(props)

        this.onClickConfig = this.onClickConfig.bind(this);
        this.onClickClone = this.onClickClone.bind(this);
        this.onClickRemove = this.onClickRemove.bind(this);
    }

    onClickConfig() {
        if (typeof this.props.onClickConfig === 'function') {
            this.props.onClickConfig();
        }
    }

    onClickClone() {
        if (typeof this.props.onClickClone === 'function') {
            this.props.onClickClone();
        }
    }

    onClickRemove() {
        if (typeof this.props.onClickRemove === 'function') {
            this.props.onClickRemove();
        }
    }

    render() {
        
        return (
            <div className="btn-toolbar" role="toolbar" style={{ display: this.props.isShow ? '' : 'none' }}>
                <button type="button" className="btn btn-default draggable-point">
                    <i className="fa fa-arrows" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default" onClick={this.onClickConfig}>
                    <i className="fa fa-cog" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default"  onClick={this.onClickClone}>
                    <i className="fa fa-clone" aria-hidden="true"></i>
                </button>
                <button type="button" className="btn btn-default" onClick={this.onClickRemove}>
                    <i className="fa fa-times" aria-hidden="true"></i>
                </button>
                {this.props.children}
            </div>
        )
    }
}

ContextMenu.defaultProps = {
    isShow: false,
    onClickConfig: null,
    onClickClone: null,
    onClickRemove: null
}