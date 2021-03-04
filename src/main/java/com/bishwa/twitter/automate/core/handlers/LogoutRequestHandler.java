package com.bishwa.twitter.automate.core.handlers;

import com.bishwa.twitter.automate.core.IAutomate;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Bishwa
 * Date: 04/03/2021
 * Time: 21:50
 */
public class LogoutRequestHandler extends IAutomate {
    private static final Logger logger = LoggerFactory.getLogger(LogoutRequestHandler.class);

    private final String TWITTER_LOGOUT_URL = "https://twitter.com/logout";

    @Override
    public void handleRequest() {
        logger.info("Login request executed");

        processNext();
    }

    private void logout() {
        driver.get(TWITTER_LOGOUT_URL);
        By logoutConfirmBtn = By.xpath("//div[@data-testid=\"confirmationSheetConfirm\"]");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}

        try {
            if (!driver.getCurrentUrl().equals(TWITTER_LOGOUT_URL)) {
                logger.info("Already logged out of twitter");
                return;
            }

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