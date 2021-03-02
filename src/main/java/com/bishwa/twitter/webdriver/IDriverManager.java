package com.bishwa.twitter.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Author: Bishwa
 * Date: 26/02/2021
 * Time: 11:15
 */
public abstract class IDriverManager {
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;

    public IDriverManager() {
        this.setupDriver();
        this.initDriver();

        webDriverWait = new WebDriverWait(driver, 10);
    }

    abstract protected void setupDriver();
    abstract protected void initDriver();

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public static Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
    }

    public static void implicitWait(Long time, TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(time, unit);
    }

    public static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    public void tearDown() {
        if(Objects.nonNull(driver)) {
            driver.quit();
        }
    }
}
