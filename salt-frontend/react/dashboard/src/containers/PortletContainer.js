import React from 'react';
import { connect } from 'react-redux';
import { init, updateLayout } from '../actions';

import {Responsive, WidthProvider} from 'react-grid-layout';
const ReactGridLayout = WidthProvider(Responsive);
import Modal from 'react-modal';

import Navbar from '../components/Navbar';
import LayoutForm from '../components/LayoutForm';
import CreatePortletComponent from '../components/CreatePortletComponent';

class PortletContainer extends React.Component {

    constructor(props) {
		super(props);
        this.onLayoutChange = this.onLayoutChange.bind(this);
	}

    componentDidMount() {
        this.props.init();
    }

    onLayoutChange(layout, layouts) {
        this.props.updateLayout(layout, layouts);
    }

    render() {
        let dashboard = this.props.dashboard;
        let PortletList = Object.keys(dashboard).map((key) => {
            let portlet = dashboard[key];
            return (
                <div key={portlet.idx} data-grid={portlet}>
                    <CreatePortletComponent
                        componentName={portlet.componentName}
                        idx={portlet.idx} 
                        padding={portlet.padding} /> 
                    
                </div>
            );
        }); 

        return (
            <div className="container">
                <Navbar>
                    <LayoutForm {...this.props.layoutConfig} />
                </Navbar>

                <div style={{ height: 60 }}></div>
                <ReactGridLayout {...this.props.layoutConfig}
                    layout={this.props.layout}
                    layouts={this.props.layouts} 
                    onLayoutChange={this.onLayoutChange}>
                    {PortletList}
                </ReactGridLayout>
            </div>
        )
    }
}

// portal item setting
PortletContainer.defaultProps = {
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
        ...state.portlet,
        layoutConfig: state.layoutConfig
    };
}

const mapDispatchToProps = (dispatch) => {
    return {
        updateLayout: (layout, layouts) => dispatch(updateLayout(layout, layouts)),
        init: () => dispatch(init())
    }
}

export default PortletContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(PortletContainer);