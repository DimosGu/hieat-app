import React, {Component} from 'react';
import {RouteHandler} from 'react-router';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';

class Root extends Component {
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
            sign: state.sign
        }
    };
}

import * as signAction from '../../actions/signAction';

function mapDispatchToProps(dispatch) {
    return {
        action: {
            sign: bindActionCreators(signAction, dispatch)
        }
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(Root);
