/**
 * Product reducer
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
import Immutable from 'immutable';

import * as DO from '../actions/goodsAction';

export default function (state = Immutable.fromJS({
    goodsList: {__loading: true}
}), action = {}) {
    switch (action.type) {
        case DO.GET_GOODS_LIST:
            return state.set('goodsList', Immutable.fromJS(action.data));

        default:
            return state;
    }
}

