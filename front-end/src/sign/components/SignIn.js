import React, {Component} from 'react'
import {Link} from 'react-router';

import SignInForm from './SignInForm';

export default class SignIn extends Component {
    componentWillReceiveProps(nextProps) {
        if (nextProps.data.sign.get('signIn').has('id')) {
            window.location.href = '/app';
        }
    }

    render() {
        return (
            <div className="container">
                <div className="card sign-form">
                    <div className="card-block">
                        <SignInForm {...this.props}/>
                        <Link to="signUp">没有账号？</Link>
                    </div>
                </div>
            </div>
        );
    }
}
