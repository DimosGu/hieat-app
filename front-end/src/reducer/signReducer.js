/**
 * 认证相关
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import Immutable from 'immutable';

import * as DO from '../actions/signAction';

export default function (state = Immutable.fromJS({
    signIn: {},
    signUp: {}
}), action = {}) {
    switch (action.type) {
        case DO.SIGN_IN:
            return state.set('signIn', Immutable.fromJS(action.data));

        case DO.SIGN_UP:
            return state.set('signUp', Immutable.fromJS(action.data));
        default:
            return state;
    }
}
