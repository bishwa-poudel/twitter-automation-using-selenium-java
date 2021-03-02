package com.bishwa.twitter.automate.conditions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Objects;

/**
 * Author: Bishwa
 * Date: 02/03/2021
 * Time: 19:24
 */
public class TwitterLoggedIn implements ExpectedCondition<Boolean> {

    @Override
    public Boolean apply(WebDriver driver) {
        List<WebElement> menu = Objects.requireNonNull(driver).findElements(By.xpath("//a[@data-testid=\"SideNav_AccountSwitcher_Button\"]"));

        if(menu.isEmpty()) {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.F5).perform();
        }

        return !menu.isEmpty();
    }
}
