/**
 * Goods List
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
import React, {Component, PropTypes} from 'react';
import GoodsListContent from './GoodsListContent';

export default class GoodsList extends Component {
    componentDidMount() {
        this.performGetProductList(this.props.routerState.query);
    }

    performGetProductList(query) {
        this.props.action.goods.getGoodsList(query)
    }

    render() {
        return (
            <div className="container">
                <GoodsListContent goodsList={this.props.data.goods.get('goodsList')}/>
            </div>
        );
    }
}

GoodsList.propTypes = {};
