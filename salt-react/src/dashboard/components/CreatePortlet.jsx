import React from 'react';

export default class CreatePortlet extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <this.props.portlet.component>
                {this.props.portlet.body}
            </this.props.portlet.component>
        )
    }
}