package com.bishwa.twitter.automate.core;

import com.bishwa.twitter.automate.conditions.TwitterLoggedIn;
import com.bishwa.twitter.automate.properties.TwitterProperties;
import com.bishwa.twitter.webdriver.IDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static final String TWITTER_FEED_URL = TwitterProperties.TWITTER_FEED_URL.val();
    private final Integer LIKE_LIMIT = TwitterProperties.LIKE_LIMIT.val().equals("") ? 0 : Integer.parseInt(TwitterProperties.LIKE_LIMIT.val());

    public void action() {
        driver.get(TWITTER_FEED_URL);
        try {
            fluentWait.until(twitterLoggedIn);
        } catch (TimeoutException e) {
            driver.navigate().refresh(); // due to instances of not signed in during the first try
            fluentWait.until(twitterLoggedIn);
        }

        AtomicInteger totalLiked = new AtomicInteger();

        while(totalLiked.get() <= LIKE_LIMIT) {
            fetchTweetLikeElements().forEach(el -> {
                try {
                    js.executeScript("arguments[0].scrollIntoView();", el);
                    Thread.sleep(3000);
                    js.executeScript("arguments[0].click();", el);

                    logger.info("Total liked : " + totalLiked.get());
                    totalLiked.getAndIncrement();

                } catch (Exception ignored) {}
            });

            js.executeScript("window.scrollBy(0,200)");
        }
    }

    private List<WebElement> fetchTweetLikeElements() {
        return driver.findElements(By.xpath("//div[@data-testid=\"like\"]"));
    }


}
