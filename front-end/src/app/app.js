/**
 * 入口
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import 'babel-core/polyfill';
import React from 'react';
import Router, {HistoryLocation} from 'react-router';
import {Provider} from 'react-redux';
import configureStore from '../store/configureStore';
import routes from './appRoute';

const store = configureStore();

Router.run(routes, HistoryLocation, (Handler, routerState) => {
    React.render(
        <Provider store={store}>
            {() => <Handler routerState={routerState}/>}
        </Provider>,
        document.getElementById('root')
    );
});
