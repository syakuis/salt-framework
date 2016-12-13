import React from 'react';
import Modal from 'react-modal';
import { Modal as bsModal } from 'react-bootstrap';

import LayoutForm from './LayoutForm';

export default class Navbar extends React.Component {

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
        this.props.addPortlet(portletName);
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

        let portletComponents = this.props.portletComponents.map((portletName, i) => {

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