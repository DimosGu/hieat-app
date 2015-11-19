import React from 'react';
import Router, {Route, Redirect} from 'react-router';

import Sign from './components/Root';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';

const routes = (
    <Route name="app" path="/sign" handler={Sign}>
        <Route name="signIn" path="in" handler={SignIn}/>
        <Route name="signUp" path="up" handler={SignUp}/>
        <Redirect path="/sign" to="signIn"/>
        <Redirect path="/sign/" to="signIn"/>
    </Route>
);

export default routes;
