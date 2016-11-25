import React from 'react';

export default class Portlet extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div>
                <this.props.component.tag>
                    {this.props.component.body}
                </this.props.component.tag>
            </div>
        )
    }
}