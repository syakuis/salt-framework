import React from 'react';
import AlloyEditor from 'alloyeditor';

export default class AlloyEditorComponent extends React.Component {
    componentDidMount() {
        this._editor = AlloyEditor.editable(this.props.container, this.props.alloyEditorConfig);
    }

    componentWillUnmount() {
        this._editor.destroy();
    }

    render() {
        return (
            <div id={this.props.container}>
            </div>
        );
    }
}
