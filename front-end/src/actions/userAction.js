/**
 * 用户相关
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
import Http from 'superagent';
import {interceptorPromise} from './common';

export const GET_USER = 'GET_USER';
export function getUser() {
    return dispatch => {
        return new Promise((resolve, reject) => {
            Http.get('/inapi/user')
                .end((err, resp) => {
                    interceptorPromise(resolve, reject, resp,
                        () => dispatch({
                            type: GET_USER,
                            data: resp.body
                        }));
                });
        });
    }
}
