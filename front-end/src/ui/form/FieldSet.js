import React, {Component} from 'react';

export default class FieldSet extends Component {
    render() {
        return (<fieldset className={this.props.className}>{this.props.children}</fieldset>);
    }
}

FieldSet.defaultProps = {
    className: 'form-group'
};
