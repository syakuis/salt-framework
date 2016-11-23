import React from 'react';
import _ from 'lodash';
import {Responsive, WidthProvider} from 'react-grid-layout';
const GridLayout = WidthProvider(Responsive);

export default class Grid extends React.Component {
    render() {
        
        return (
            <GridLayout className="layout">
            </GridLayout>
        )
    }
}