import React from 'react';
import { connect } from 'react-redux';
import { setLayoutConfigMargin, setLayoutConfigContainerPadding, setLayoutConfigRowHeight} from '../actions';

class LayoutForm extends React.Component {
	constructor(props) {
		super(props);

		this.setMargin = this.setMargin.bind(this);
		this.setPadding = this.setPadding.bind(this);
		this.setRowHeight = this.setRowHeight.bind(this);
	}

	state = {
		marginX: this.props.margin[0],
		marginY: this.props.margin[1],
		paddingX: this.props.containerPadding[0],
		paddingY: this.props.containerPadding[1],
		rowHeight: this.props.rowHeight
	}

	setMargin(e) {
		let name = e.target.name;
		let value = parseFloat(e.target.value);

		let margin = { ...this.state, [name]: value };
		this.setState({ [name]: value });
		this.props.setLayoutConfigMargin(margin.marginX, margin.marginY);
	}

	setPadding(e) {
		let name = e.target.name;
		let value = parseFloat(e.target.value);

		let result = { ...this.state, [name]: value };
		this.setState({ [name]: value });
		this.props.setLayoutConfigContainerPadding(result.paddingX, result.paddingY);
	}

	setRowHeight(e) {
		let value = parseFloat(e.target.value);
		this.setState({ rowHeight: value });
		this.props.setLayoutConfigRowHeight(value);
	}

	render() {
		return (
			<div className="panel panel-default">
				<div className="panel-body">
					<div className="form-group">
						<label htmlFor="margin">margin</label>
						<div className="row">
							<div className="col-md-6">
								<input type="text" className="form-control" placeholder="marginX"
								name="marginX"
								onChange={this.setMargin}
								value={this.state.marginX} />
							</div>
							<div className="col-md-6">
								<input type="text" className="form-control" placeholder="marginY"
								name="marginY"
								onChange={this.setMargin}
								value={this.state.marginY} />
							</div>
						</div>
					</div>
					<div className="form-group">
						<label htmlFor="padding">padding</label>
						<div className="row">
							<div className="col-md-6">
								<input type="text" className="form-control" placeholder="paddingX"
									name="paddingX"
									onChange={this.setPadding}
									value={this.state.paddingX} />
							</div>
							<div className="col-md-6">
								<input type="text" className="form-control" placeholder="paddingY"
									name="paddingY"
									onChange={this.setPadding}
									value={this.state.paddingY} />
							</div>
						</div>
					</div>
					<div className="form-group">
						<label htmlFor="height">height</label>
						<input type="text" className="form-control" placeholder="height" 
						onChange={this.setRowHeight}
						value={this.state.rowHeight}
						/>
					</div>
				</div>
			</div>
		);
	}
}

const mapDispatchToProps = (dispatch) => {
    return {
        setLayoutConfigMargin: (x,y) => dispatch(setLayoutConfigMargin(x,y)),
        setLayoutConfigContainerPadding: (x,y) => dispatch(setLayoutConfigContainerPadding(x,y)),
        setLayoutConfigRowHeight: (height) => dispatch(setLayoutConfigRowHeight(height))
    }
}

export default LayoutForm = connect(
  undefined,
  mapDispatchToProps
)(LayoutForm);