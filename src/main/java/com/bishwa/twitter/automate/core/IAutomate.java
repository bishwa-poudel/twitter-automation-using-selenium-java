package com.bishwa.twitter.automate.core;

import com.bishwa.twitter.webdriver.IDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.Objects;

/**
 * Author: Bishwa
 * Date: 04/03/2021
 * Time: 21:37
 */
public abstract class IAutomate {
    protected static final WebDriver driver = IDriverManager.getDriver();
    protected final Wait<WebDriver> fluentWait = IDriverManager.getFluentWait();
    protected final JavascriptExecutor js = IDriverManager.getJSExecutor();

    protected IAutomate next;

    public IAutomate next(IAutomate next) {
        this.next = next;
        return next;
    }

    public abstract void handleRequest();

    protected void processNext() {
        if(Objects.isNull(next)) return;

        next.handleRequest();
    }
}
