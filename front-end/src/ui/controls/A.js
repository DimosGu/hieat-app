import React, {Component} from 'react';

export default class A extends Component {
    render() {
        return (<a {...this.props}>{this.props.children}</a>)
    }
}

A.defaultProps = {
    href: 'javascript:;'
};
