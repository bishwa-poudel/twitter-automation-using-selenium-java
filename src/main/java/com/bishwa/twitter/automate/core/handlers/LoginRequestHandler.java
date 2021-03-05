package com.bishwa.twitter.automate.core.handlers;

import com.bishwa.twitter.automate.core.conditions.TwitterLoggedIn;
import com.bishwa.twitter.automate.core.IAutomate;
import com.bishwa.twitter.automate.properties.TwitterProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Bishwa
 * Date: 04/03/2021
 * Time: 21:43
 */
public class LoginRequestHandler extends IAutomate {
    private static final Logger logger = LoggerFactory.getLogger(LoginRequestHandler.class);
    private static final String TWITTER_LOGIN_URL = "https://twitter.com/login";

    private static final TwitterLoggedIn twitterLoggedIn = new TwitterLoggedIn();

    @Override
    public void handleRequest() {
        logger.info("Login request executed");

        login();

        processNext();
    }

    private void login() {
        driver.get(TWITTER_LOGIN_URL);

        if(checkIfLoggedIn()) {
            logger.info("Already logged in to twitter");
            return;
        }

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"session[username_or_email]\"]")));

        WebElement usernameField = driver.findElement(By.xpath("//input[@name=\"session[username_or_email]\"]"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name=\"session[password]\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//div[@data-testid=\"LoginForm_Login_Button\"]"));

        usernameField.sendKeys(TwitterProperties.USERNAME.val());
        passwordField.sendKeys(TwitterProperties.PASSWORD.val());
        loginButton.click();

        fluentWait.until(twitterLoggedIn);

        logger.info("Logged into twitter");
    }

    private boolean checkIfLoggedIn() {
        return !driver.getCurrentUrl().equals(TWITTER_LOGIN_URL);
    }
}
