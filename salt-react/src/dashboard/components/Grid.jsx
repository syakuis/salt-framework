import React from 'react';
import LinkedStateMixin from 'react-addons-linked-state-mixin';

import _ from 'lodash';
import {Responsive, WidthProvider} from 'react-grid-layout';
const GridLayout = WidthProvider(Responsive);

export default class Grid extends React.Component {

    constructor(props) {
		super(props);

        this.createBox = this.createBox.bind(this);
        this.initDataBind = this.initDataBind.bind(this);
	}

    state = {
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

    getProps() {
        return Object.assign({}, this.props.box);
    }

    createBox() {
        let layouts = this.state.layouts;
        let box = this.getProps();
        this.setState({
            box: box,
            layouts: [ 
                ...layouts,
                this.state.box
            ]
        });
        console.log(this.props.box, this.state.box);
    }

    initDataBind(e) {
        let datatype = e.target.attributes.getNamedItem('datatype');
        let value = e.target.value;

        if (datatype != null) {
            datatype = datatype.nodeValue;
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
                        <input type="number" className="form-control" placeholder="width" 
                            name="w" 
                            datatype="number"
                            onChange={this.initDataBind} 
                            value={this.state.box.w} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="height">height</label>
                        <input type="number"  placeholder="height" className="form-control" 
                            name="h" 
                            datatype="number"
                            onChange={this.initDataBind} 
                            value={this.state.box.h} />
                    </div>
                    
                    <div className="form-group">
                        <label htmlFor="static">static</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" 
                            name="static"
                            datatype="boolean" 
                            checked={this.state.box.static}
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
                                checked={this.state.box.isDraggable} /> 사용
                        </label>
                    </div>
                    <div className="form-group">
                        <label htmlFor="resizable">resizable</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" 
                                name="isResizable" 
                                datatype="boolean" 
                                onChange={this.initDataBind}  
                                checked={this.state.box.isResizable} /> 사용
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
                <h3>{JSON.stringify(this.state.box)} {this.state.layouts.length}</h3>
                <GridLayout className="layout">
                {body}
                </GridLayout>
            </div>
        )
    }
}

Grid.defaultProps = {
    box: {
        x: 0,
        y: 0,
        w: 1,
        h: 2,
        static: false,
        isDraggable: true,
        isResizable: true
    }
}
