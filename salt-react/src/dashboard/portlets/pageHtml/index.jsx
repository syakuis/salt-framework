import React from 'react';
import ContextMenu from '../../components/ContextMenu.jsx';
import AlloyEditorComponent from '../../components/AlloyEditorComponent.jsx';

export default class PageHtml extends React.Component {
    render() {
        return (
            <div>
                <ContextMenu 
                    onClickConfig={this.onModalOpen}
                    onClickClone={() => this.props.onCopyPortlet(this.props.index)}
                    onClickRemove={() => this.props.onDelPortlet(this.props.index)}
                    isShow={this.props.isContextMenuShow} />

                <AlloyEditorComponent container="editable"/>
            </div>
        )
    }
} 