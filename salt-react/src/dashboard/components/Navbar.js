import React from 'react';

import { connect } from 'react-redux';
import { addPortlet } from '../actions';

import LayoutForm from './LayoutForm';

class Navbar extends React.Component {

    constructor(props) {
        super(props);

        this.onShowPortletList = this.onShowPortletList.bind(this);
        this.onShowLayoutForm = this.onShowLayoutForm.bind(this);
    }

    state = {
        isShowPortletList : false,
        isShowLayoutForm: false
    }

    onShowPortletList() {
        this.setState({ isShowPortletList: !this.state.isShowPortletList });
    }

    onShowLayoutForm() {
        this.setState({ isShowLayoutForm: !this.state.isShowLayoutForm });
    }

    onAddPortlet(portletName) {
        let portletComponents = this.props.getPortletComponents();
        let portlet = portletComponents[portletName];
        portlet = portlet.getDefault();
        portlet['componentName'] = portletName;

        this.props.addPortlet(portlet);
    }


    render() {
        let showPortletListStyle = { position: 'absolute', marginTop: 5 };
        if (!this.state.isShowPortletList) {
            showPortletListStyle['display'] = 'none';
        }

        let showLayoutFormStyle = { position: 'absolute', marginTop: 5, width: 200 };
        if (!this.state.isShowLayoutForm) {
            showLayoutFormStyle['display'] = 'none';
        }

        let portletComponents = Object.keys(this.props.getPortletComponents()).map((portletName, i) => {

            return (
                <div key={i} onClick={() => this.onAddPortlet(portletName)}>
                    {portletName}
                </div>
            );
        }); 

        return (
            <nav className="navbar navbar-default navbar-fixed-top">
                <div className="container">
                    <div className="collapse navbar-collapse">
                        <ul className="nav navbar-nav">
                            <li>
                                <a href="#" onClick={this.onShowPortletList}><i className="fa fa-plus"></i> 포틀릿추가</a>
                                <div style={showPortletListStyle}>
                                    <div className="panel panel-default">
                                        <div className="panel-body">
                                            {portletComponents}
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li>
                                <a href="#" onClick={this.onShowLayoutForm}><i className="fa fa-cog"></i> 설정</a>

                                <div style={showLayoutFormStyle}>
                                    {this.props.children}
                                </div>
                            </li>
                            <li><a href="#"><i className="fa fa-check"></i> 저장</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

const mapStateToProps = (state) => {
    return state.portletComponents;
}

const mapDispatchToProps = (dispatch) => {
    return {
        addPortlet: (portlet) => dispatch(addPortlet(portlet))
    }
}

export default Navbar = connect(
  mapStateToProps,
  mapDispatchToProps
)(Navbar);