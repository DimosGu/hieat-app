import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

export default class GoodsListContent extends Component {
    renderProducts(content) {
        return content.map((goods, idx) => {
            const goodsId = goods.get('id');
            return (
                <div key={idx} className="card">
                    <Link to="goodsItem" params={{goodsId: goodsId}}>
                        <img className="card-img-top" style={{width: '100%'}}
                             src={goods.get('images').get(0)}/>
                    </Link>

                    <div className="card-block">
                        <p>
                            <Link to="goodsItem" params={{goodsId: goodsId}}>
                                {goods.get('name')}
                            </Link>
                        </p>

                        <p>{goods.get('attrs').get('description')}</p>

                        <div>{'¥' + goods.get('price')}</div>
                    </div>
                </div>);
        });
    }

    render() {
        return (
            <div className={this.props.className}>
                {this.props.goodsList.get('__loading')
                    ? <div>载入中……</div>
                    : (
                    this.props.goodsList.get('content').count() > 0
                        ? this.renderProducts(this.props.goodsList.get('content'))
                        : <div>暂无数据</div>)}
            </div>
        );
    }
}

GoodsListContent.propTypes = {
    goodsList: PropTypes.object.isRequired
};

GoodsListContent.defaultProps = {
    className: 'card-columns'
};
