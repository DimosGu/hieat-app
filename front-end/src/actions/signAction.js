/**
 * 用户权限相关
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import Http from 'superagent';
import {interceptorPromise} from './common';

export const SIGN_IN = 'SIGN_IN';
export function signIn(payload) {
    return dispatch => {
        return new Promise((resolve, reject) => {
            Http.post('/inapi/user/signIn')
                .send(payload)
                .end((error, resp) => {
                    interceptorPromise(resolve, reject, resp,
                        () => dispatch({
                            type: SIGN_IN,
                            data: resp.body
                        }));
                });
        });
    }
}

export const SIGN_UP = 'SIGN_UP';
export function signUp(payload) {
    return dispatch => {
        return new Promise((resolve, reject) => {
            Http.post('/inapi/user/signUp')
                .send(payload)
                .end((error, resp) => {
                    interceptorPromise(resolve, reject, resp,
                        () => dispatch({
                            type: SIGN_UP,
                            data: resp.body
                        }));
                });
        });
    }
}

export const GET_SMS_VALID_CODE = 'GET_SMS_VALID_CODE';
/**
 *
 * @param payload {account:'', code:''}
 * @returns {Function}
 */
export function smsValidCode(payload) {
    return dispatch => {
        return new Promise((resolve, reject) => {
            Http.post('/inapi/user/smsValidCode')
                .send(payload)
                .end((err, resp) => {
                    interceptorPromise(resolve, reject, resp,
                        () => dispatch({
                            type: GET_SMS_VALID_CODE,
                            data: {status: resp.status}
                        }));
                });
        });
    }
}
