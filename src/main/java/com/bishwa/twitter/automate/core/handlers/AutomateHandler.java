package com.bishwa.twitter.automate.core.handlers;

import com.bishwa.twitter.automate.core.IAutomate;

/**
 * Author: Bishwa
 * Date: 04/03/2021
 * Time: 21:44
 */
public class AutomateHandler extends IAutomate {
    @Override
    public void handleRequest() {
        processNext();
    }
}
