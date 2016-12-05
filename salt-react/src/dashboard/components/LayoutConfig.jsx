import React from 'react';

export default class LayoutConfig extends React.Component {
	constructor(props) {
		super(props);

		this.setMargin = this.setMargin.bind(this);
	}

	state = {
		marginX: this.props.margin[0],
		marginY: this.props.margin[1]
	}

	setMarginX(e) {
		let value = Number.parseFloat(e.target.value);
		this.setState({marginX: value});

		this.props.setMargin(this.state.marginX, this.state.marginY);
	}

	setMarginY(e) {
		let name = e.target.name;
		let value = Number.parseFloat(e.target.value);
		this.setState({[name]: value});

		this.props.setMargin(this.state.marginX, this.state.marginY);
	}

	render() {
		return (
			<div>
				<div className="panel panel-default">
					<div className="panel-body">
						<form>
							<div className="form-group">
								<label htmlFor="margin">margin</label>
								<input type="text" className="form-control" placeholder="marginX"
									name="marginX"
									onChange={this.setMargin}
									value={this.state.marginX} 
								/>
								<input type="text" className="form-control" placeholder="marginY"
									name="marginY"
									onChange={this.setMargin}
									value={this.state.marginY}
								/>
							</div>
							<div className="form-group">
								<label htmlFor="padding">padding</label>
								<input type="text" className="form-control" placeholder="paddingX" />
								<input type="text" className="form-control" placeholder="paddingY" />
							</div>
							<div className="form-group">
								<label htmlFor="height">height</label>
								<input type="text" className="form-control" placeholder="height" />
							</div>
						</form>
					</div>
				</div>
			</div>
		);
	}
}