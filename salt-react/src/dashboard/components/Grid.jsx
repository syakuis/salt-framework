import React from 'react';
import LinkedStateMixin from 'react-addons-linked-state-mixin';

import _ from 'lodash';
import {Responsive, WidthProvider} from 'react-grid-layout';
const GridLayout = WidthProvider(Responsive);

export default class Grid extends React.Component {

    constructor(props) {
		super(props);

        this.createBox = this.createBox.bind(this);
        this.isAble = this.isAble.bind(this);
	}

    state = {
        box: {
            i: '',
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
        this.setState({
            layouts: [ 
                ...layouts,
                {x: 0, y: 0, w: 1, h: 2, static: true}
            ]
        });
    }

    isAble(e) {
        console.log(this.state.box[e.target.name]);
    }

    render() {
        let body = this.state.layouts.map((data, i) => {
            return (
                <div key={i}>
                    <span className="text" title="This item is static and cannot be removed or resized.">Static - {i}</span>
                    <span className="text">{i}</span>
                </div>
            );
        });

        let form = (
            <div className="container">
                <form className="form-inline">
                    
                    <div className="form-group">
                        <label htmlFor="static">static</label>
                        <label className="radio-inline">
                            <input type="radio" name="static" onChange={this.isAble} value="true" checked={this.state.box.static === true} /> 예
                        </label>
                        <label className="radio-inline">
                            <input type="radio" name="static" onChange={this.isAble} value="false" checked={this.state.box.static === false} /> 아니오
                        </label>
                    </div>
                    <div className="form-group">
                        <label htmlFor="draggable">draggable</label>
                        <label className="radio-inline">
                            <input type="radio" name="draggable" onChange={this.isAble} value="true" checked={this.state.box.draggable === true} /> 예
                        </label>
                        <label className="radio-inline">
                            <input type="radio" name="draggable" onChange={this.isAble} value="false" checked={this.state.box.draggable === false} /> 아니오
                        </label>
                    </div>
                    <div className="form-group">
                        <label htmlFor="resizable">resizable</label>
                        <label className="radio-inline">
                            <input type="radio" name="resizable" onChange={this.isAble} value="true" checked={this.state.box.resizable === true} /> 예
                        </label>
                        <label className="radio-inline">
                            <input type="radio" name="resizable" onChange={this.isAble} value="false" checked={this.state.box.resizable === false} /> 아니오
                        </label>
                    </div>
                    <button className="btn btn-default" type="button" onClick={this.createBox}>생성</button>
                </form>
            </div>
        );

        return (
            <div>
                {form}
                <GridLayout className="layout">
                {body}
                </GridLayout>
            </div>
        )
    }
}