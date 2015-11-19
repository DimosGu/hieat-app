import React, {Component, PropTypes} from 'react';

export default class Footer extends Component {
    render() {
        return (
            <div className="footer navbar-dark">
                <div className="container">
                    <div className="row">
                        <div className="col-xs-12 col-md-4">
                        </div>
                        <div className="col-xs-12 col-md-4">
                        </div>
                        <div className="col-xs-12 col-md-4">
                            <ul className="list-unstyled">
                                <li>
                                    <a target="_blank" title="Source code for Github"
                                       className="nav-link"
                                       href="https://github.com/yangbajing/hieat-app">
                                        Source code@Github
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Footer.propTypes = {
    action: PropTypes.object.isRequired,
    data: PropTypes.object.isRequired
};
