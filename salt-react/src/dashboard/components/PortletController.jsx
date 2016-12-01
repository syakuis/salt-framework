import React from 'react';

import _ from 'lodash';
import {Responsive, WidthProvider} from 'react-grid-layout';
const ReactGridLayout = WidthProvider(Responsive);
import Modal from 'react-modal';

import CreatePortlet from './CreatePortlet';
import * as Portlets from '../portlets';

export default class PortletController extends React.Component {

    constructor(props) {
		super(props);

        this.addPortlet = this.addPortlet.bind(this);
        this.initDataBind = this.initDataBind.bind(this);
        this.delPortlet = this.delPortlet.bind(this);
        this.copyPortlet = this.copyPortlet.bind(this);   
	}

    state = {
        config: {
            width: 120,
            autoSize: true,
            //cols: {lg: 12, md: 10, sm: 6, xs: 4, xxs: 2},
            //breakpoints: {lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0},
            //draggableCancel: '',
            draggableHandle: '.draggable-point',
            verticalCompact: true,
            layout: [],
            margin: [10,10],
            containerPadding: [10,10],
            rowHeight: 150,
            isDraggable: true,
            isResizable: true,
            useCSSTransforms: true
        },
        box: {
            portlet: '',
            x: 0,
            y: Infinity,
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

    addPortlet() {
        let layouts = this.state.layouts
        let box = this.getProps()
        this.setState({
            box: box,
            layouts: [ 
                ...layouts,
                this.state.box
            ]
        })
    }

    delPortlet(i) {
        let layouts = this.state.layouts;
        layouts.splice(i, 1);
        this.setState({ layouts: layouts });
    }

    copyPortlet(i) {
        let layout = this.state.layouts[i];
        this.setState({
            layouts: [ 
                ...this.state.layouts,
                layout
            ]
        })
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

        this.setState(Object.assign(this.state.box, {[e.target.name]: value}));
    }

    render() {

        let selectBoxPortlet = this.props.portlets.map((name, i) => {
            return (
                <option key={i} value={name}>{name}</option>
            );
        });

        let body = this.state.layouts.map((data, i) => {
            return (
                <div key={i} data-grid={data}>
                    <CreatePortlet
                        onDelPortlet={this.delPortlet} 
                        onCopyPortlet={this.copyPortlet}
                        index={i} 
                        portlet={{ component: Portlets[data.portlet], body: '' }} /> 
                </div>
            );
        });

        let form = (
            <div className="container">
                <form className="form-inline">
                    <div className="form-group">
                        <label htmlFor="portlets">portlets</label>
                        <select className="form-control"
                                name="portlet" 
                                datatype="string" 
                                onChange={this.initDataBind}
                                value={this.state.box.portlet}>
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
                    <button className="btn btn-default" type="button" onClick={this.addPortlet}>생성</button>
                </form>
            </div>
        );

        return (
            <div>
                {form}
                <hr />
                <h3>{JSON.stringify(this.state.box)} {this.state.layouts.length}</h3>
                <ReactGridLayout className="layout" {...this.state.config}>
                {body}
                </ReactGridLayout>
            </div>
        )
    }
}

function getPortlets() {
    return Object.keys(Portlets);
}

PortletController.defaultProps = {
    portlets: getPortlets(),
    box: {
        portlet: '',
        x: 0,
        y: Infinity,
        w: 1,
        h: 2,
        static: false,
        isDraggable: true,
        isResizable: true
    }
}

Modal.defaultStyles = {
    overlay : {
        position          : 'fixed',
        top               : 0,
        left              : 0,
        right             : 0,
        bottom            : 0,
        backgroundColor   : 'rgba(255, 255, 255, 0.75)'
    },
    content : {
        position                   : 'absolute',
        border                     : '1px solid #ccc',
        background                 : '#fff',
        overflow                   : 'auto',
        WebkitOverflowScrolling    : 'touch',
        borderRadius               : '10px',
        outline                    : 'none',
        padding                    : '20px',

        top                   : '50%',
        left                  : '50%',
        right                 : 'auto',
        bottom                : 'auto',
        marginRight           : '-50%',
        transform             : 'translate(-50%, -50%)'
    }
}