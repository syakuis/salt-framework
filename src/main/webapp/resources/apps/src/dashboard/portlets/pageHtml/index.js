import React from 'react';
import ContextMenu from '../../components/ContextMenu';
import AlloyEditorComponent from '../../components/AlloyEditorComponent';

export default class PageHtml extends React.Component {
    constructor(props) {
        super(props);
    }

    static getDefault() {
        return {
            padding: 5,
            w: 4,
            h: 4,
            x: 0,
            y: Infinity,
            static: false,
            isDraggable: true,
            isResizable: true
        };
    }

    render() {
        let padding = this.props.padding;
        if (padding <= 0) {
            padding = 5;
        }

        return (
            <div style={{ width: '100%', height: '100%', padding: padding }}>
                <ContextMenu idx={this.props.idx} isContextMenuShow={this.props.isContextMenuShow} />
                <AlloyEditorComponent container="editable"/>
            </div>
        )
    }
}