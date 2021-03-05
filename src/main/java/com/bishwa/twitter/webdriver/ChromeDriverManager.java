package com.bishwa.twitter.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Author: Bishwa
 * Date: 26/02/2021
 * Time: 11:18
 */
public class ChromeDriverManager extends IDriverManager {

    @Override
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1366,768");
//        options.addArguments("--window-size=1366,768");

        driver = new ChromeDriver(options);
    }
}
