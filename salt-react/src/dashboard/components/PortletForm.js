import React from 'react';

export default class PortletFrom extends React.Component {

	constructor(props) {
		super(props);

		this.initDataBind = this.initDataBind.bind(this);
		this.onAddPortlet = this.onAddPortlet.bind(this);
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
                value = parseFloat(value);
            break;
            case 'boolean':
                if (typeof value === 'boolean') value = Boolean(value);
            break;
        }

        this.setState({ [e.target.name]: value });
    }

	onAddPortlet() {
		this.setState({ ...this.props.portlet });
		this.props.addPortlet(this.state);
	}

	render() {

		return (
			<div className="panel panel-default">
				<div className="panel-body">
					<form>

						<div className="form-group">
							<label htmlFor="componentName">componentName</label>
							<select className="form-control"
									name="componentName" 
									datatype="string" 
									onChange={this.initDataBind}
									value={this.state.componentName}>
								<option value="">포틀릿선택</option>
								{ this.props.portletComponents.map((name, i) => <option key={i} value={name}>{name}</option> )}
							</select>
						</div>

						<div className="form-group">
							<label htmlFor="padding">padding</label>
							<input type="text" className="form-control" placeholder="padding"
								name="padding"
								datatype="number" 
								onChange={this.initDataBind}
								value={this.state.padding} 
								/>
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
						<button className="btn btn-default" type="button" onClick={this.onAddPortlet}>생성</button>
					</form>
				</div>
			</div>
		);
	}
}