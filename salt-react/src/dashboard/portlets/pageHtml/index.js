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
        let {padding, ...styles} = this.props.style;
        if (padding <= 0) {
            padding = 5;
        }

        return (
            <div style={{...styles, padding: padding}}>
                <ContextMenu {...this.props} />
                <div>
                <AlloyEditorComponent container="editable"/>
                </div>
            </div>
        )
    }
}