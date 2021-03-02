package com.bishwa.twitter.automate.core;

import com.bishwa.twitter.automate.conditions.TwitterLoggedIn;
import com.bishwa.twitter.webdriver.IDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Author: Bishwa
 * Date: 02/03/2021
 * Time: 19:14
 */
public class LikeAction {
    @Inject
    private TwitterLoggedIn twitterLoggedIn;

    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private static final WebDriver driver = IDriverManager.getDriver();
    private final Wait<WebDriver> fluentWait = IDriverManager.getFluentWait();
    private final JavascriptExecutor js = IDriverManager.getJSExecutor();

    private static final String TWITTER_HASHTAG_URL = "https://twitter.com/hashtag/100DaysOfCode?f=live";
    private static final String TWITTER_ADVANCED_URL = "https://twitter.com/search?q=(Day)%20-Visit%20(%23100DaysOfCode%20OR%20%23javascript%20OR%20%23CodeNewbie%20OR%20%23DEVCommunity%20OR%20%23CodeNewbies%20OR%20%23WomenWhoCode%20OR%20%23programming%20OR%20%23java)%20lang%3Aen%20-filter%3Alinks%20-filter%3Areplies&src=typed_query&f=live";
    private final Integer LIKE_LIMIT = 10;

    public void action() {
        driver.get(TWITTER_HASHTAG_URL);
        fluentWait.until(twitterLoggedIn);

        IntStream.rangeClosed(1, 30).forEach(iter -> {
            fetchTweetLikeElements().forEach(el -> {
                js.executeScript("arguments[0].scrollIntoView();", el);
                js.executeScript("arguments[0].click();", el);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            });

            js.executeScript("window.scrollBy(0,250)");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}

        });

    }

    private List<WebElement> fetchTweetLikeElements() {
        return driver.findElements(By.xpath("//div[@data-testid=\"like\"]"));
    }


}
