package com.bishwa.twitter.automate.core;

import com.bishwa.twitter.automate.properties.TwitterProperties;
import com.bishwa.twitter.webdriver.IDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Bishwa
 * Date: 01/03/2021
 * Time: 16:21
 */
public class LoginAction {
    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private static final WebDriver driver = IDriverManager.getDriver();
    private final Wait<WebDriver> fluentWait = IDriverManager.getFluentWait();

    private static final String TWITTER_LOGIN_URL = "https://twitter.com/login";

    public void action() {
        driver.get(TWITTER_LOGIN_URL);
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"session[username_or_email]\"]")));

        WebElement usernameField = driver.findElement(By.xpath("//input[@name=\"session[username_or_email]\"]"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name=\"session[password]\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//div[@data-testid=\"LoginForm_Login_Button\"]"));

        usernameField.sendKeys(TwitterProperties.USERNAME.val());
        passwordField.sendKeys(TwitterProperties.PASSWORD.val());
        loginButton.click();

        // await for some element to appear
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-testid=\"AppTabBar_Home_Link\"]")));

        logger.info("Logged into twitter");
    }
}
