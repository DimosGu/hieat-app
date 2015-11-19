import React, {Component} from 'react';

export class Button extends Component {
    render() {
        return (<button {...this.props}>{this.props.children}</button>);
    }
}

Button.defaultProps = {
    className: 'btn btn-default',
    type: 'button'
};
