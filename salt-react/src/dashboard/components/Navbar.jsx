import React from 'react';
import Modal from 'react-modal';

export default class Navbar extends React.Component {

    constructor(props) {
        super(props);

        this.onShowPortletList = this.onShowPortletList.bind(this);
    }

    state = {
        isShowPortletList : false
    }

    onShowPortletList() {
        this.setState({ isShowPortletList: !this.state.isShowPortletList });
    }

    onAddPortlet(portletName) {
        this.props.addPortlet(portletName);
    }


    render() {
        let style = { position: 'absolute', marginTop: 5 };
        if (!this.state.isShowPortletList) {
            style['display'] = 'none';
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
                                <a href="#" onClick={this.onShowPortletList}>포틀릿추가</a>
                                <div style={style}>
                                    <div className="panel panel-default">
                                        <div className="panel-body">
                                            {portletComponents}
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li><a href="#">설정</a></li>
                            <li><a href="#">저장</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}