import React from 'react';
import ContextMenu from '../../components/ContextMenu.jsx';
import AlloyEditorComponent from '../../components/AlloyEditorComponent.jsx';

export default class PageHtml extends React.Component {
    render() {
        return (
            <div>
                <ContextMenu 
                    onClickConfig={this.onModalOpen}
                    onClickClone={() => this.props.clonePortlet(this.props.idx)}
                    onClickRemove={() => this.props.deletePortlet(this.props.idx)}
                    isShow={this.props.isContextMenuShow} />

                <AlloyEditorComponent container="editable"/>
            </div>
        )
    }
} 