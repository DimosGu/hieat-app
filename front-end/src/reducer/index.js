/**
 * reducers
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import {combineReducers} from 'redux';

import signReducer from './signReducer';
import userReducer from './userReducer';
import goodsReducer from './goodsReducer';

const rootReducer = combineReducers({
    sign: signReducer,
    user: userReducer,
    goods: goodsReducer
});

export default rootReducer;
