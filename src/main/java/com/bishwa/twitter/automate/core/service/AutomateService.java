package com.bishwa.twitter.automate.core.service;

import com.bishwa.twitter.automate.core.IAutomate;
import com.bishwa.twitter.automate.core.handlers.AutomateHandler;
import com.bishwa.twitter.automate.core.handlers.LikeRequestHandler;
import com.bishwa.twitter.automate.core.handlers.LoginRequestHandler;
import com.bishwa.twitter.automate.core.handlers.LogoutRequestHandler;

/**
 * Author: Bishwa
 * Date: 05/03/2021
 * Time: 13:34
 */
public class AutomateService {
    private final IAutomate iAutomate = new AutomateHandler();

    public void login() {
        iAutomate.next(new LoginRequestHandler());
        iAutomate.handleRequest();
    }

    public void like() {
        iAutomate.next(new LoginRequestHandler())
                .next(new LikeRequestHandler());
//                .next(new LogoutRequestHandler());
        iAutomate.handleRequest();
    }

    public void logout() {
        iAutomate.next(new LogoutRequestHandler());
        iAutomate.handleRequest();
    }
}
