import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

import NavbarMenu from './NavbarMenu';
import NavbarUser from './NavbarUser';
import SignLinks from './SignLinks';

class NavbarTop extends Component {
    render() {
        return (
            <nav className={this.props.className}>
                <div className="container">
                    {this.props.children}
                </div>
            </nav>
        );
    }
}
NavbarTop.defaultProps = {
    className: 'navbar navbar-fixed-top navbar-dark bg-inverse'
};


export default class Navbar extends Component {
    render() {
        if (this.props.data.user && this.props.data.user.get('userInfo').has('id')) {
            return (
                <NavbarTop>
                    <Link className="navbar-brand" to="app">Hieat</Link>
                    <NavbarMenu {...this.props}/>
                    <NavbarUser {...this.props}/>
                </NavbarTop>
            );
        } else {
            return (
                <NavbarTop>
                    <Link className="navbar-brand" to="app">Hieat</Link>
                    <SignLinks/>
                </NavbarTop>
            );
        }
    }
}

Navbar.propTypes = {
    action: PropTypes.object.isRequired,
    data: PropTypes.object.isRequired
};
