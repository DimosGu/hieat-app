import React, {Component} from 'react';
import {Link} from 'react-router';

export default class NavbarMenu extends Component {
    render() {
        return (
            <ul className="nav navbar-nav">
                <li className="nav-item">
                    <Link className="nav-link" to="goods">商品</Link>
                </li>
            </ul>
        );
    }
}
