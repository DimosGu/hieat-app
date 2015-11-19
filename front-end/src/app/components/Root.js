/**
 * Root Component and redux connect react
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
import React, {Component} from 'react';
import {RouteHandler} from 'react-router';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';

class Root extends Component {
    componentDidMount() {
        this.props.action.user.getUser()
            .catch(resp => {
            });
    }

    render() {
        return (
            <div>
                <Navbar {...this.props}/>

                <div className="main-content">
                    <RouteHandler {...this.props}/>
                </div>
                <Footer {...this.props}/>
            </div>
        );
    }
}


function mapStateToProps(state) {
    return {
        data: {
            user: state.user,
            goods: state.goods
        }
    };
}

import * as userAction from '../../actions/userAction';
import * as goodsAction from '../../actions/goodsAction';

function mapDispatchToProps(dispatch) {
    return {
        action: {
            user: bindActionCreators(userAction, dispatch),
            goods: bindActionCreators(goodsAction, dispatch)
        }
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Root);
