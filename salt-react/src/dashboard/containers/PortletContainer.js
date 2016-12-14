import React from 'react';
import update from 'react-addons-update';
import _ from 'lodash';

import { connect } from 'react-redux';
import { addPortlet } from '../actions';

import {Responsive, WidthProvider} from 'react-grid-layout';
const ReactGridLayout = WidthProvider(Responsive);
import Modal from 'react-modal';

import Navbar from '../components/Navbar';
import LayoutForm from '../components/LayoutForm';
import PortletForm from '../components/PortletForm';
import CreatePortletComponent from '../components/CreatePortletComponent';
import * as PortletComponents from '../portlets';

class PortletContainer extends React.Component {

    constructor(props) {
		super(props);

        this.setLayoutConfigMargin = this.setLayoutConfigMargin.bind(this);
        this.setLayoutConfigContainerPadding = this.setLayoutConfigContainerPadding.bind(this);
        this.setLayoutConfigRowHeight = this.setLayoutConfigRowHeight.bind(this);
        this.onLayoutChange = this.onLayoutChange.bind(this);

        this.addPortlet = this.addPortlet.bind(this);
        this.addPortlet2 = this.addPortlet2.bind(this);
        this.updatePortlet = this.updatePortlet.bind(this);
        this.deletePortlet = this.deletePortlet.bind(this);
        this.clonePortlet = this.clonePortlet.bind(this);
	}

    state = {
        layoutConfig: {
            ...this.props.layoutConfig
        },
        layout: [],
        layouts: {},
        dashboard: {},
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
        let idx = this.props.createPortletIdx();
        portlet['idx'] = idx;

        this.setState({
            dashboard: update(this.state.dashboard, {$merge: { [idx]: portlet }})
        });
    }

    addPortlet2(portletName) {
        let portlet = PortletComponents[portletName];
        portlet = portlet.getDefault();
        portlet['componentName'] = portletName;
        console.log(portlet);

        this.props.addPortlet(portlet);
    }

    updatePortlet(portlet) {
        let newPortlet = {...this.state.dashboard[portlet.idx], ...portlet};
        this.setState({
            dashboard: update(this.state.dashboard, {$merge: { [portlet.idx]: newPortlet }})
        });

    }

    deletePortlet(idx) {
        this.setState({ dashboard: _.omit(this.state.dashboard, [idx]) });
    }

    clonePortlet(idx) {
        let newIdx = this.createPortletIdx();
        let portlet = { ...this.state.dashboard[idx], idx: newIdx };

        this.setState({
           dashboard: update(this.state.dashboard, {$merge: { [newIdx]: portlet }}) 
        });
    }

    render() {
        let dashboard = this.props.dashboard;
        let PortletList = Object.keys(dashboard).map((key) => {
            let portlet = dashboard[key];
            return (
                <div key={portlet.idx} data-grid={portlet}>
                    <CreatePortletComponent
                        updatePortlet={this.updatePortlet}
                        deletePortlet={this.deletePortlet} 
                        clonePortlet={this.clonePortlet}
                        portlet={portlet}
                        idx={portlet.idx} 
                        padding={portlet.padding}
                        portletComponent={PortletComponents[portlet.componentName]} /> 
                    
                </div>
            );
        }); 
        /*let PortletList = this.state.dashboard.map((portlet, i) => {
            return (
                <div key={portlet.idx} data-grid={portlet}>
                    <CreatePortletComponent
                        updatePortlet={this.updatePortlet}
                        deletePortlet={this.deletePortlet} 
                        clonePortlet={this.clonePortlet}
                        portlet={portlet}
                        idx={portlet.idx} 
                        padding={portlet.padding}
                        portletComponent={PortletComponents[portlet.componentName]} /> 
                    
                </div>
            );
        });*/

        return (
            <div className="container">
                <Navbar portletComponents={this.props.portletComponents} addPortlet={this.addPortlet2}>
                    <LayoutForm {...this.state.layoutConfig} 
                        setMargin={this.setLayoutConfigMargin}
                        setPadding={this.setLayoutConfigContainerPadding}
                        setRowHeight={this.setLayoutConfigRowHeight} />
                </Navbar>

                <div style={{ height: 60 }}></div>
                <ReactGridLayout {...this.state.layoutConfig}
                    layout={this.state.layout}
                    layouts={this.state.layouts} 
                    onLayoutChange={this.onLayoutChange}>
                    {PortletList}
                </ReactGridLayout>
            </div>
        )
    }
}

// portal item setting
PortletContainer.defaultProps = {
    portletComponents: Object.keys(PortletComponents),
    layoutConfig: {
        className: "layout",
        autoSize: true,
        cols: {lg: 12, md: 10, sm: 6, xs: 4, xxs: 2},
        breakpoints: {lg: 1200, md: 996, sm: 768, xs: 480, xxs: 0},
        //draggableCancel: '',
        draggableHandle: '.draggable-point',
        verticalCompact: true,
        //layout: [],
        //layouts: {},
        margin: [5,5],
        containerPadding: [0,0],
        rowHeight: 30,
        isDraggable: true,
        isResizable: true,
        useCSSTransforms: true
    },
    portlet: {
        componentName: '',
        idx: '',
        padding: 0,
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

const mapStateToProps = (state) => {
    return {
        ...state.portlet
    };
}

const mapDispatchToProps = (dispatch) => {
	return {
      addPortlet: (portlet) => dispatch(addPortlet(portlet))
	}
}

export default PortletContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(PortletContainer);