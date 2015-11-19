import React, {Component} from 'react';

import A from '../ui/controls/A';

export default class NavbarUser extends Component {
    render() {
        const userInfo = this.props.data.user.get('userInfo');
        const userAttrs = userInfo.get('attrs');
        return (
            <ul className="nav navbar-nav pull-right">
                <li className="nav-item">
                    <A className="nav-link">{userAttrs.get('nick') || userAttrs.get('phone') || userInfo.get('id')}</A>
                </li>
            </ul>
        );
    }
}
