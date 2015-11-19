import React, {Component} from 'react';

export default class Form extends Component {
    render() {
        return (
            <form className={this.props.className}
                  onSubmit={this.props.onSubmit}>
                {this.props.children}
            </form>);
    }
}

Form.defaultProps = {
    onSubmit: function (evt) {
        evt.preventDefault();
        console.warn('default Form.onSubmit');
    }
};
