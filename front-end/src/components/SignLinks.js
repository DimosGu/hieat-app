import React, {Component} from 'react';

export default class SignLinks extends Component {
    render() {
        return (
            <ul className={this.props.className}>
                <li className="nav-item">
                    <a className="nav-link" href="/sign/in">登录</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="/sign/up">注册</a>
                </li>
            </ul>
        );
    }
}

SignLinks.defaultProps = {
    className: 'nav navbar-nav pull-right'
};
