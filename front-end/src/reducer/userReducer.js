/**
 * User reducer
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
import Immutable from 'immutable';

import * as DO from '../actions/userAction';

export default function (state = Immutable.fromJS({
    userInfo: {}
}), action = {}) {
    switch (action.type) {
        case DO.GET_USER:
            return state.set('userInfo', Immutable.fromJS(action.data));

        default:
            return state;
    }
}

