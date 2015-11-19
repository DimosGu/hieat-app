/**
 * 产品相关
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
import Http from 'superagent';
import {interceptorPromise} from './common';

export const GET_GOODS_LIST = 'GET_GOODS_LIST';
export function getGoodsList(query) {
    return dispatch => {
        return new Promise((resolve, reject) => {
            Http.get('/inapi/goods/list')
                .query(query || {})
                .end((err, resp) => {
                    interceptorPromise(resolve, reject, resp,
                        () => dispatch({
                            type: GET_GOODS_LIST,
                            data: resp.body
                        }));
                });
        });
    }
}
