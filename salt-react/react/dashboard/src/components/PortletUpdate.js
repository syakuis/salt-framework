import React from 'react';

import { connect } from 'react-redux';
import { updatePortlet } from '../actions';

class PortletUpdate extends React.Component {

	constructor(props) {
		super(props);

		this.initDataBind = this.initDataBind.bind(this);
		this.onUpdatePortlet = this.onUpdatePortlet.bind(this);
	}

	state = {
		...this.props.dashboard[this.props.idx]
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

        this.setState({[e.target.name]: value });
    }

	onUpdatePortlet() {
		this.props.updatePortlet(this.state);
		this.props.onModalClose();
	}

	render() {
		
		return (
			<div>
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
				<button className="btn btn-default" type="button" onClick={this.onUpdatePortlet}>수정</button>
			</div>
		);
	}
}

const mapStateToProps = (state) => {
    return {
		dashboard: state.portlet.dashboard
    };
}

const mapDispatchToProps = (dispatch) => {
    return {
        updatePortlet: (portlet) => dispatch(updatePortlet(portlet))
    }
}

export default PortletUpdate = connect(
  mapStateToProps,
  mapDispatchToProps
)(PortletUpdate);