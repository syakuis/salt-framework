import React from 'react';
import _ from 'lodash';

import {Responsive, WidthProvider} from 'react-grid-layout';
const ReactGridLayout = WidthProvider(Responsive);
import Modal from 'react-modal';

import LayoutConfig from './LayoutConfig'
import PortletForm from './PortletForm';
import CreatePortlet from './CreatePortlet';
import * as PortletComponents from '../portlets';

export default class PortletController extends React.Component {

    constructor(props) {
		super(props);

        this.setLayoutConfigMargin = this.setLayoutConfigMargin.bind(this);
        this.setLayoutConfigContainerPadding = this.setLayoutConfigContainerPadding.bind(this);
        this.setLayoutConfigRowHeight = this.setLayoutConfigRowHeight.bind(this);
        this.onLayoutChange = this.onLayoutChange.bind(this);

        this.addPortlet = this.addPortlet.bind(this);
        this.initDataBind = this.initDataBind.bind(this);
        this.delPortlet = this.delPortlet.bind(this);
        this.copyPortlet = this.copyPortlet.bind(this);
        this.onShow = this.onShow.bind(this);   
	}

    state = {
        show: false,
        layoutConfig: {
            className: "layout",
            autoSize: true,
            //cols: {lg: 12, md: 10, sm: 6, xs: 4, xxs: 2},
            //breakpoints: {lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0},
            //draggableCancel: '',
            draggableHandle: '.draggable-point',
            verticalCompact: true,
            layout: [],
            margin: [10,10],
            containerPadding: [30,30],
            rowHeight: 150,
            isDraggable: true,
            isResizable: true,
            useCSSTransforms: true
        },
        portlet: {
            portletName: '',
            index: 0,
            x: 0,
            y: Infinity,
            w: 1,
            h: 2,
            static: false,
            isDraggable: true,
            isResizable: true
        },
        result: [
        ]
    }

    getProps() {
        return Object.assign({}, this.props.portlet);
    }

    getIndex() {
        return this.state.result.length + 1;
    }

    updateLayoutConfig(config) {
        this.setState.layoutConfig = {
            ...this.state.config,
            config
        };
    }

    setLayoutConfigMargin(x, y) {
        this.setState({ 
            layoutConfig: {
                ...this.state.layoutConfig,
                margin: [x, y]
            }
        });
    }

    setLayoutConfigContainerPadding(x, y) {
        this.setState({ 
            layoutConfig: {
                ...this.state.layoutConfig,
                containerPadding: [x, y]
            }
        });
    }

    setLayoutConfigRowHeight(height) {
        this.setState({ 
            layoutConfig: {
                ...this.state.layoutConfig,
                rowHeight: height
            }
        });
    }

    onLayoutChange(layout) {
        console.log(layout);
    }

    addPortlet() {
        let portlet = {
            ...this.state.portlet,
            index: this.getIndex()
        };

        this.setState({
            portlet: this.getProps(),
            result: [ 
                ...this.state.result,
                portlet
            ]
        });
    }

    delPortlet(index) {
        this.setState({ result: _.reject(this.state.result, { index: index }) });
    }

    copyPortlet(index) {
        let layout = Object.assign({}, _.find(this.state.result, {index: index}), { index: this.getIndex()});

        this.setState({
            result: [ 
                ...this.state.result,
                layout
            ]
        });
    }

    onShow() {
        this.setState({ show: true });
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

        this.setState(Object.assign(this.state.portlet, {[e.target.name]: value}));
    }

    render() {

        let selectBoxPortlet = this.props.portletComponents.map((name, i) => {
            return (
                <option key={i} value={name}>{name}</option>
            );
        });

        let showPortlet = (this.state.show) ? <PortletForm /> : '';

        let body = this.state.result.map((data, i) => {
            return (
                <div key={i} data-grid={data} onClick={this.onShow}>
                    <CreatePortlet
                        onDelPortlet={this.delPortlet} 
                        onCopyPortlet={this.copyPortlet}
                        index={data.index} 
                        portlet={{ component: PortletComponents[data.portletName], body: '' }} /> 

                </div>
            );
        });

        let CreatePortletForm = (
            <div className="container">
                <form className="form-inline">
                    <div className="form-group">
                        <label htmlFor="portlets">portlets</label>
                        <select className="form-control"
                                name="portletName" 
                                datatype="string" 
                                onChange={this.initDataBind}
                                value={this.state.portlet.portletName}>
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
                            value={this.state.portlet.w} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="height">height</label>
                        <input type="number"  placeholder="height" className="form-control" 
                            name="h" 
                            datatype="number"
                            onChange={this.initDataBind} 
                            value={this.state.portlet.h} />
                    </div>
                    
                    <div className="form-group">
                        <label htmlFor="static">static</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" 
                            name="static"
                            datatype="boolean" 
                            checked={this.state.portlet.static}
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
                                checked={this.state.portlet.isDraggable} /> 사용
                        </label>
                    </div>
                    <div className="form-group">
                        <label htmlFor="resizable">resizable</label>
                        <label className="checkbox-inline">
                            <input type="checkbox" 
                                name="isResizable" 
                                datatype="boolean" 
                                onChange={this.initDataBind}  
                                checked={this.state.portlet.isResizable} /> 사용
                        </label>
                    </div>
                    <button className="btn btn-default" type="button" onClick={this.addPortlet}>생성</button>
                </form>
            </div>
        );

        return (
            <div>
                <LayoutConfig {...this.state.layoutConfig} 
                    setMargin={this.setLayoutConfigMargin}
                    setPadding={this.setLayoutConfigContainerPadding}
                    setRowHeight={this.setLayoutConfigRowHeight}
                    />
                {CreatePortletForm}
                <hr />
                <h3>{JSON.stringify(this.state.result)} {this.state.result.length}</h3>
                <ReactGridLayout className="layout" {...this.state.layoutConfig}
                    onLayoutChange={this.onLayoutChange}>
                {body}
                </ReactGridLayout>
            </div>
        )
    }
}

function getPortletComponents() {
    return Object.keys(PortletComponents);
}

// portal item setting
PortletController.defaultProps = {
    portletComponents: getPortletComponents(),
    portlet: {
        portletName: '',
        x: 0,
        y: Infinity,
        w: 1,
        h: 2,
        static: false,
        isDraggable: true,
        isResizable: true
    }
}

// modal setting
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