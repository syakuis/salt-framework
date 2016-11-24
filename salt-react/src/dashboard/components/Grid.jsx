import React from 'react';
import LinkedStateMixin from 'react-addons-linked-state-mixin';

import _ from 'lodash';
import {Responsive, WidthProvider} from 'react-grid-layout';
const GridLayout = WidthProvider(Responsive);

export default class Grid extends React.Component {

    constructor(props) {
		super(props);

        this.createBox = this.createBox.bind(this);
        this.setValue = this.setValue.bind(this);
	}

    state = {
        default: {
            x: 0,
            y: 0,
            w: 1,
            h: 2,
            static: false,
            isDraggable: true,
            isResizable: true
        },
        box: {
            x: 0,
            y: 0,
            w: 1,
            h: 2,
            static: false,
            isDraggable: true,
            isResizable: true
        },
        layouts: [
        ]
    }

    createBox() {
        let layouts = this.state.layouts;
        console.log(this.state);
        this.setState({
            box: this.state.default,
            layouts: [ 
                ...layouts,
                this.state.box
            ]
        });
    }

    setValue(e) {
        let value = e.target.value;
        if (e.target.type === 'checkbox') {
            value = (value === 'Y' && e.target.checked) ? true : false; 
        }

        this.setState(Object.assign(this.state.box, {[e.target.name]: value}));
    }

    render() {
        let body = this.state.layouts.map((data, i) => {
            return (
                <div key={i} data-grid={data}>
                    <span className="text" title="This item is static and cannot be removed or resized.">Static - {i}</span>
                    <span className="text">{i}</span>
                </div>
            );
        });

        let form = (
            <div className="container">
                <form className="form-inline">

                    <div className="form-group">
                        <label htmlFor="width">width</label>
                        <input type="number" className="form-control" placeholder="width" name="w" onChange={this.setValue} value={this.state.box.w} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="height">height</label>
                        <input type="number" className="form-control" placeholder="height" name="h" onChange={this.setValue} value={this.state.box.h} />
                    </div>
                    
                    <div className="form-group">
                        <label htmlFor="static">static</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" name="static" onChange={this.setValue} value="Y" checked={this.state.box.static} /> 사용
                        </label>
                    </div>
                    <div className="form-group">
                        <label htmlFor="draggable">draggable</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" name="isDraggable" onChange={this.setValue} value="Y" checked={this.state.box.isDraggable} /> 사용
                        </label>
                    </div>
                    <div className="form-group">
                        <label htmlFor="resizable">resizable</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" name="isResizable" onChange={this.setValue} value="Y" checked={this.state.box.isResizable} /> 사용
                        </label>
                    </div>
                    <button className="btn btn-default" type="button" onClick={this.createBox}>생성</button>
                </form>
            </div>
        );

        return (
            <div>
                {form}
                <hr />
                <h1>{this.state.box.w}, {this.state.box.static}</h1>
                <GridLayout className="layout">
                {body}
                </GridLayout>
            </div>
        )
    }
}