import React from 'react';
import _ from 'lodash';
import {Responsive, WidthProvider} from 'react-grid-layout';
const GridLayout = WidthProvider(Responsive);

export default class Grid extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            layouts: []
        };
    }

    item() {
        let layouts = this.state.layouts;
        this.setState({
            layouts: [ 
                ...layouts,
                {x: 0, y: 0, w: 1, h: 2, static: true}
            ]
        });
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

        return (
            <div>
                <button onClick={this.item}>Generate New Layout</button>
                <GridLayout className="layout">
                {body}
                </GridLayout>
            </div>
        )
    }
}