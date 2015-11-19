/**
 * app routes
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import React from 'react';
import Router, {Route, DefaultRoute, Redirect, RouteHandler} from 'react-router';

import Root from './components/Root';
import GoodsRoute from './components/goods/GoodsRoute';
import GoodsList from './components/goods/GoodsList';
import GoodsItem from './components/goods/GoodsItem';

const routes = (
    <Route name="app" path="/app" handler={Root}>
        <Route name="goods" path="goods" handler={GoodsRoute}>
            <DefaultRoute name="goodsList" handler={GoodsList}/>
            <Route name="goodsItem" path=":goodsId" handler={GoodsItem}/>
        </Route>
        <Redirect from="/app" to="goodsList"/>
    </Route>
);

export default routes;
