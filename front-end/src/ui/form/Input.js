import React, {Component, PropTypes} from 'react';

export class Input extends Component {
    handleChange(evt) {
        evt.preventDefault();
        let value = evt.target.value;
        if (value) {
            switch (this.props.type) {
                case 'number':
                    value = Number(value);
                    break;
            }
        }
        this.props.onChange(value, this.props);
    }

    render() {
        return (<input {...this.props} onChange={this.handleChange.bind(this)}/>);
    }
}

Input.defaultProps = {
    type: 'text',
    className: 'form-control'
};

Input.propTypes = {
    name: PropTypes.string.isRequired,
    /**
     * @param value: Any
     * @param type: String
     * @param oldValue: Any
     */
    onChange: PropTypes.func.isRequired,
    value: PropTypes.any,
    placeHolder: PropTypes.any
};
