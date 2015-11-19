/**
 * store
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import createLogger from 'redux-logger';
import Immutable from 'immutable';

import rootReducer from '../reducer';

const logger = createLogger({
    collapsed: true,
    transformer: (state) => {
        return Object.keys(state).map((key) => {
            if (Immutable.Iterable.isIterable(state[key])) {
                return state[key].toJS();
            } else {
                return state[key];
            }
        });
    },
    predicate: (getState, action) => true,
    level: 'info'
});

const createStoreWithMiddleware = applyMiddleware(
    thunk,
    logger
)(createStore);

export default function configureStore(initialState) {
    return createStoreWithMiddleware(rootReducer, initialState);
};
