import React, {Component} from 'react'

import A from '../../ui/controls/A';

import Form from '../../ui/form/Form';
import FieldSet from '../../ui/form/FieldSet';
import {Input} from '../../ui/form/Input';
import {Button} from '../../ui/button/Button';

const CAPTCHA_URI = '/inapi/user/captcha';

export default class SignInForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: {
                account: '',
                password: ''
            },
            captchaUri: CAPTCHA_URI,
            errorMessage: null
        }
    }

    handleRefreshCaptcha(evt) {
        evt.preventDefault();
        this.setState({captchaUri: CAPTCHA_URI + '?' + new Date().getTime()});
    }

    handleControlChange(v, prop) {
        let value = Object.assign({}, this.state.value);
        value[prop.name] = v;
        let state = {value: value};
        if (!this.state.errorMessage) {
            state.errorMessage = null;
        }
        this.setState(state);
    }

    handleSubmit(evt) {
        evt.preventDefault();
        this.props.action.sign.signIn(this.state.value)
            .then(resp => {
                console.info('signIn success', resp.body);
            })
            .catch(resp => {
                this.setState({
                    captchaUri: CAPTCHA_URI + '?' + new Date().getTime(),
                    errorMessage: resp.body.message
                });
            });
    }

    render() {
        return (
            <Form className={this.props.className}
                  onSubmit={this.handleSubmit.bind(this)}>
                <FieldSet>
                    <Input type="tel"
                           tabIndex="1"
                           placeholder="手机号"
                           name="account"
                           value={this.state.value.account}
                           onChange={this.handleControlChange.bind(this)}/>
                </FieldSet>
                <FieldSet>
                    <Input type="password"
                           tabIndex="2"
                           placeholder="密码"
                           name="password"
                           value={this.state.value.password}
                           onChange={this.handleControlChange.bind(this)}/>
                </FieldSet>
                <FieldSet className="form-group row">
                    <div className="col-xs-5">
                        <A onClick={this.handleRefreshCaptcha.bind(this)}
                           title="点击图片刷新">
                            <img className="captcha" src={this.state.captchaUri}/>
                        </A>
                    </div>
                    <div className="col-xs-7">
                        <Input type="text"
                               tabIndex="3"
                               placeholder="图片验证码"
                               name="code"
                               value={this.state.value.code}
                               placeholder="点击图片刷新验证码"
                               onChange={this.handleControlChange.bind(this)}/>
                    </div>
                </FieldSet>
                <FieldSet>
                    <Button type="submit" className="btn btn-primary btn-block">登录</Button>

                    <p className="text-danger">{this.state.errorMessage}</p>
                </FieldSet>
            </Form>
        );
    }
}

SignInForm.defaultProps = {
    //className: 'sign-form'
};
