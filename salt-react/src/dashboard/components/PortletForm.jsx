import React from 'react';
import Draggable from 'react-draggable';

export default class PortletFrom extends React.Component {

	constructor(props) {
		super(props);

		this.initDataBind = this.initDataBind.bind(this);
		this.onClickAddPortlet = this.onClickAddPortlet.bind(this);
	}

	state = {
		...this.props.portlet
	}


    initDataBind(e) {
        let datatype = e.target.attributes.getNamedItem('datatype');
        let value = e.target.value;

        if (datatype != null) {
            datatype = datatype.value;
        }

        switch(e.target.type) {
            case 'checkbox':
                value = e.target.checked;
            break;
            default:
            break;
        }

        switch(datatype) {
            case 'number':
                value = Number.parseFloat(value);
            break;
            case 'boolean':
                if (typeof value === 'boolean') value = Boolean(value);
            break;
        }

        this.setState(Object.assign(this.state, {[e.target.name]: value}));
    }

	onClickAddPortlet() {
		this.setState({ ...this.props.portlet });
		this.props.addPortlet(this.state);
	}

	render() {

        let selectBoxPortlet = this.props.portletComponents.map((name, i) => {
            return (
                <option key={i} value={name}>{name}</option>
            );
        });

		return (
			<Draggable>
				<div className="panel panel-default">
					<div className="panel-body">
						<form className="form-inline">
							<div className="form-group">
								<label htmlFor="componentName">componentName</label>
								<select className="form-control"
										name="componentName" 
										datatype="string" 
										onChange={this.initDataBind}
										value={this.state.componentName}>
									<option value="">포틀릿선택</option>
									{selectBoxPortlet}
								</select>
							</div>

							<div className="form-group">
								<label htmlFor="width">width</label>
								<input type="number" className="form-control" placeholder="width" 
									name="w" 
									datatype="number"
									onChange={this.initDataBind} 
									value={this.state.w} />
							</div>

							<div className="form-group">
								<label htmlFor="height">height</label>
								<input type="number"  placeholder="height" className="form-control" 
									name="h" 
									datatype="number"
									onChange={this.initDataBind} 
									value={this.state.h} />
							</div>
							
							<div className="form-group">
								<label htmlFor="static">static</label>
								<label className="checkbox-inline">
									<input type="checkbox" 
									name="static"
									datatype="boolean" 
									checked={this.state.static}
									onChange={this.initDataBind} /> 사용
								</label>
							</div>
							<div className="form-group">
								<label htmlFor="draggable">draggable</label>
								<label className="checkbox-inline">
									<input type="checkbox" 
										name="isDraggable" 
										datatype="boolean" 
										onChange={this.initDataBind} 
										checked={this.state.isDraggable} /> 사용
								</label>
							</div>
							<div className="form-group">
								<label htmlFor="resizable">resizable</label>
								<label className="checkbox-inline">
									<input type="checkbox" 
										name="isResizable" 
										datatype="boolean" 
										onChange={this.initDataBind}  
										checked={this.state.isResizable} /> 사용
								</label>
							</div>
							<button className="btn btn-default" type="button" onClick={this.onClickAddPortlet}>생성</button>
						</form>
					</div>
				</div>
			</Draggable>
		);
	}
}