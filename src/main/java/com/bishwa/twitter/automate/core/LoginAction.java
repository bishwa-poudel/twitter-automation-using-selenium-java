package com.bishwa.twitter.automate.core;

import com.bishwa.twitter.automate.conditions.TwitterLoggedIn;
import com.bishwa.twitter.automate.properties.TwitterProperties;
import com.bishwa.twitter.webdriver.IDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Author: Bishwa
 * Date: 01/03/2021
 * Time: 16:21
 */
public class LoginAction {
    @Inject
    private TwitterLoggedIn twitterLoggedIn;

    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private static final WebDriver driver = IDriverManager.getDriver();
    private final Wait<WebDriver> fluentWait = IDriverManager.getFluentWait();

    private static final String TWITTER_LOGIN_URL = "https://twitter.com/login";

    public void action() {
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
