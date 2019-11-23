package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseHelper {
    protected FirefoxDriver wd;

    public BaseHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void select(By locator1, String textOnSelectedItem, By locator2) {
        wd.findElement(locator1).click();
        new Select(wd.findElement(locator1)).selectByVisibleText(textOnSelectedItem);
        wd.findElement(locator2).click();
    }
}
