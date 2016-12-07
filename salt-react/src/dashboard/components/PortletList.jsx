import React from 'react';

import CreatePortletComponent from './CreatePortletComponent';
import * as PortletComponents from '../portlets';

export default class PortletList extends React.Component {

	constructor(props) {
		super(props)
	}

	render() {

		let body = this.props.dashboard.map((portlet, i) => {
            return (
                <div key={portlet.idx} data-grid={portlet}>
                    <CreatePortletComponent
                        deletePortlet={this.deletePortlet} 
                        clonePortlet={this.clonePortlet}
                        idx={portlet.idx} 
                        portletComponent={PortletComponents[portlet.componentName]} /> 

                </div>
            );
        });

		return (
			<div>
				{body}
			</div>
		);
	}
}