package com.bishwa.twitter.automate.core;

import com.bishwa.twitter.webdriver.IDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Bishwa
 * Date: 03/03/2021
 * Time: 21:18
 */
public class LogoutAction {
    private static final Logger logger = LoggerFactory.getLogger(LogoutAction.class);
    private static final WebDriver driver = IDriverManager.getDriver();
    private final Wait<WebDriver> fluentWait = IDriverManager.getFluentWait();

    private final String TWITTER_LOGOUT_URL = "https://twitter.com/logout";

    public void action() {
        driver.get(TWITTER_LOGOUT_URL);
        By logoutConfirmBtn = By.xpath("//div[@data-testid=\"confirmationSheetConfirm\"]");

        try {
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(logoutConfirmBtn));
        } catch (TimeoutException e) {
            logger.info("Already logged out of twitter");
            return;
        }

        WebElement logoutConfirmElement = driver.findElement(logoutConfirmBtn);
        logoutConfirmElement.click();

        logger.info("Logged out of twitter");
    }

}
