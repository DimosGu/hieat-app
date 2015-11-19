import React, {Component} from 'react';
import {RouteHandler} from 'react-router';

export default class GoodsRoute extends Component {
    render() {
        return <RouteHandler {...this.props}/>
    }
}