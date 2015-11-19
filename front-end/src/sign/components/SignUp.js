import React, {Component} from 'react'
import {Link} from 'react-router';

import SignUpForm from './SignUpForm';

export default class SignUp extends Component {
    render() {
        return (
            <div className="container">
                <div className="card sign-form">
                    <div className="card-block">
                        <SignUpForm {...this.props}/>
                        <Link to="signIn">已有账号</Link>
                    </div>
                </div>
            </div>
        );
    }
}
