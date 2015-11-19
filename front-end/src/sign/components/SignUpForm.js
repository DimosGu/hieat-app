import React, {Component, PropTypes} from 'react'
import {Link} from 'react-router';

import Utils from '../../common/Utils';

import A from '../../ui/controls/A';

import Form from '../../ui/form/Form';
import FieldSet from '../../ui/form/FieldSet';
import {Input} from '../../ui/form/Input';
import {Button} from '../../ui/button/Button';

const CAPTCHA_URI = '/inapi/user/captcha';

export default class SignUpForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: {
                account: '',
                password: '',
                password2: '',
                code: '',
                smsCode: ''
            },
            captchaUri: CAPTCHA_URI,
            smsValidCodeLabel: '获取短信验证码',
            errorMessage: null,
            successMessage: null
        }
    }

    handleRefreshCaptcha(evt) {
        evt.preventDefault();
        this.setState({captchaUri: CAPTCHA_URI + '?' + new Date().getTime()});
    }

    handleControlChange(v, prop) {
        let value = Object.assign({}, this.state.value);
        value[prop.name] = v;
        this.setState({value: value, errorMessage: null});
    }

    handleSmsValidCode(evt) {
        evt.preventDefault();
        if (!Utils.REGEX.PHONE.test(this.state.value.account)) {
            this.setState({errorMessage: '请输入有效的手机号'});
            return;
        }

        this.props.action.sign.smsValidCode({account: this.state.value.account, code: this.state.value.code})
            .then(resp => {
                window.alert('获取短信验证码成功,请输入6位数字验证码!')
            })
            .catch(resp => {
                this.setState({
                    captchaUri: CAPTCHA_URI + '?' + new Date().getTime(),
                    errorMessage: resp.body.message
                });
            });
    }

    handleSubmit(evt) {
        evt.preventDefault();
        if (!Utils.REGEX.PHONE.test(this.state.value.account)) {
            this.setState({errorMessage: '请输入有效的手机号'});
            return;
        }

        if (!Utils.REGEX.SMS_CODE.test(this.state.value.smsCode)) {
            this.setState({errorMessage: '请输入短信验证码(当前请随意输入6位数字)'});
            return;
        }

        this.props.action.sign.signUp(this.state.value)
            .then(resp => {
                console.info('signUp success', resp.body);
                setTimeout(() => this.context.router.transitionTo('signIn'), 1900);
                this.setState({successMessage: '注册成功, 2秒后跳转到登录页面'});
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
                           className="form-control"
                           placeholder="手机号"
                           name="account"
                           value={this.state.value.account}
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
                               tabIndex="2"
                               name="code"
                               value={this.state.value.code}
                               placeholder="点击图片刷新验证码"
                               onChange={this.handleControlChange.bind(this)}/>
                    </div>
                </FieldSet>
                <FieldSet className="form-group clearfix">
                    <div className="input-group">
                        <Input type="text"
                               tabIndex="3"
                               placeholder="输入短信验证码"
                               name="smsCode"
                               value={this.state.value.smsCode}
                               onChange={this.handleControlChange.bind(this)}/>
                            <span className="input-group-btn">
                                <button className="btn btn-secondary" type="button"
                                        onClick={this.handleSmsValidCode.bind(this)}>
                                    {this.state.smsValidCodeLabel}
                                </button>
                            </span>
                    </div>
                </FieldSet>
                <FieldSet>
                    <Input type="password"
                           tabIndex="4"
                           className="form-control"
                           placeholder="密码"
                           name="password"
                           value={this.state.value.password}
                           onChange={this.handleControlChange.bind(this)}/>
                </FieldSet>
                <FieldSet>
                    <Input type="password"
                           tabIndex="5"
                           className="form-control"
                           placeholder="确认密码"
                           name="password2"
                           value={this.state.value.password2}
                           onChange={this.handleControlChange.bind(this)}/>
                </FieldSet>
                <FieldSet>
                    <Button type="submit" className="btn btn-primary btn-block">注册</Button>

                    <p className="text-danger">{this.state.errorMessage}</p>

                    <p className="text-success">{this.state.successMessage}</p>
                </FieldSet>
            </Form>
        );
    }
}

SignUpForm.contextTypes = {
    router: PropTypes.func
};

SignUpForm.defaultProps = {
    //className: 'sign-form'
};