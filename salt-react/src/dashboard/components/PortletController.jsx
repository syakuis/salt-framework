import React from 'react';
import _ from 'lodash';

import {Responsive, WidthProvider} from 'react-grid-layout';
const ReactGridLayout = WidthProvider(Responsive);
import Modal from 'react-modal';

import LayoutForm from './LayoutForm'
import PortletForm from './PortletForm';
import CreatePortletComponent from './CreatePortletComponent';
import * as PortletComponents from '../portlets';

export default class PortletController extends React.Component {

    constructor(props) {
		super(props);

        this.setLayoutConfigMargin = this.setLayoutConfigMargin.bind(this);
        this.setLayoutConfigContainerPadding = this.setLayoutConfigContainerPadding.bind(this);
        this.setLayoutConfigRowHeight = this.setLayoutConfigRowHeight.bind(this);
        this.onLayoutChange = this.onLayoutChange.bind(this);

        this.addPortlet = this.addPortlet.bind(this);
        this.deletePortlet = this.deletePortlet.bind(this);
        this.clonePortlet = this.clonePortlet.bind(this);
	}

    state = {
        layoutConfig: {
            ...this.props.layoutConfig
        },
        layout: [],
        layouts: {},
        dashboard: [],
        show: false,
        portletCount: 0
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

    updateLayoutConfig(config) {
        this.setState.layoutConfig = {
            ...this.state.layoutConfig,
            config
        };
    }


    onLayoutChange(layout, layouts) {
        this.setState({
            layout: layout,
            layouts: layouts
        });
    }

    createPortletIdx() {
        let idx = 'idx_' + this.state.portletCount;
        this.setState({ portletCount: this.state.portletCount + 1 });
        return idx;
    }

    addPortlet(portlet) {
        this.setState({
            dashboard: this.state.dashboard.concat({
                ...portlet,
                idx: this.createPortletIdx()
            })
        });
    }

    deletePortlet(idx) {
        this.setState({ dashboard: _.reject(this.state.dashboard, { idx: idx }) });
    }

    clonePortlet(idx) {
        let portlet = Object.assign(
            {}, _.find(this.state.dashboard, { idx: idx }),
            { 
                x: this.state.dashboard.length * 2 % (this.state.cols || 12),
                idx: this.createPortletIdx() 
            });

        this.setState({
            dashboard: [ 
                ...this.state.dashboard,
                portlet
            ]
        });
    }

    render() {

        let body = this.state.dashboard.map((portlet, i) => {
            return (
                <div key={portlet.idx} data-grid={portlet} onClick={this.onShow}>
                    <CreatePortletComponent
                        deletePortlet={this.deletePortlet} 
                        clonePortlet={this.clonePortlet}
                        idx={portlet.idx} 
                        portletComponent={PortletComponents[portlet.componentName]} /> 

                </div>
            );
        });

        return (
            <div>
                <LayoutForm {...this.state.layoutConfig} 
                    setMargin={this.setLayoutConfigMargin}
                    setPadding={this.setLayoutConfigContainerPadding}
                    setRowHeight={this.setLayoutConfigRowHeight} />
                <PortletForm addPortlet={this.addPortlet} portletComponents={this.props.portletComponents} portlet={this.props.portlet} />
                <hr />
                <p>{JSON.stringify(this.state)} ( {this.state.dashboard.length} )</p>
                <ReactGridLayout {...this.state.layoutConfig} onLayoutChange={this.onLayoutChange}>
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
    layoutConfig: {
        className: "layout",
        autoSize: false,
        cols: {lg: 12, md: 10, sm: 6, xs: 4, xxs: 2},
        //breakpoints: {lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0},
        //draggableCancel: '',
        draggableHandle: '.draggable-point',
        verticalCompact: true,
        //layout: [],
        //layouts: {},
        margin: [10,10],
        containerPadding: [30,30],
        rowHeight: 30,
        isDraggable: true,
        isResizable: true,
        useCSSTransforms: true
    },
    portlet: {
        componentName: '',
        idx: '',
        w: 1,
        h: 2,
        x: 0,
        y: Infinity,
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