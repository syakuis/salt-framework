import React from 'react';
import ContextMenu from '../../components/ContextMenu.jsx';
import AlloyEditorComponent from '../../components/AlloyEditorComponent.jsx';

export default class PageHtml extends React.Component {
    constructor(props) {
        super(props);
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