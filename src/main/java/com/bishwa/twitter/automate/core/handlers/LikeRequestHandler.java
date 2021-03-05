package com.bishwa.twitter.automate.core.handlers;

import com.bishwa.twitter.automate.conditions.TwitterLoggedIn;
import com.bishwa.twitter.automate.core.IAutomate;
import com.bishwa.twitter.automate.properties.TwitterProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Bishwa
 * Date: 04/03/2021
 * Time: 21:50
 */
public class LikeRequestHandler extends IAutomate {
    private static final Logger logger = LoggerFactory.getLogger(LikeRequestHandler.class);

    private static final String TWITTER_FEED_URL = TwitterProperties.TWITTER_FEED_URL.val();
    private final Integer LIKE_LIMIT = TwitterProperties.LIKE_LIMIT.val().equals("") ? 0 : Integer.parseInt(TwitterProperties.LIKE_LIMIT.val());

    private static final TwitterLoggedIn twitterLoggedIn = new TwitterLoggedIn();

    @Override
    public void handleRequest() {
        logger.info("Like request executed");

        like();

        processNext();
    }

    private void like() {
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

                    Thread.sleep(Objects.equals(TwitterProperties.DELAY_IN_MS.val(), "") ? 1000L : Long.parseLong(TwitterProperties.DELAY_IN_MS.val()));

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
