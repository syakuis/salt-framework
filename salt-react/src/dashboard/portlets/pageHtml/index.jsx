import React from 'react';
import ContextMenu from '../../components/ContextMenu.jsx';
import AlloyEditorComponent from '../../components/AlloyEditorComponent.jsx';

export default class PageHtml extends React.Component {
    render() {
        return (
            <div>
                <ContextMenu {...this.props} />
                <AlloyEditorComponent container="editable"/>
            </div>
        )
    }
} 