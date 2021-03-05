package com.bishwa.twitter.automate.core.conditions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        List<WebElement> menu = Objects.requireNonNull(driver).findElements(By.xpath("//div[@data-testid=\"SideNav_AccountSwitcher_Button\"]"));

        return !menu.isEmpty();
    }
}
