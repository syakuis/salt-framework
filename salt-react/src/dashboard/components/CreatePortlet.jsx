import React from 'react';

export default class CreatePortlet extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        let { portlet, ...props } = this.props; 
        return (
            <portlet.component {...props}>
                {this.props.portlet.body}
            </portlet.component>
        )
    }
}