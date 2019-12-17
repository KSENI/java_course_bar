package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }

    }

    protected void select(By locator1, String textOnSelectedItem, By locator2) {
        wd.findElement(locator1).click();
        new Select(wd.findElement(locator1)).selectByVisibleText(textOnSelectedItem);
        wd.findElement(locator2).click();
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    String getText(By locator) {
        return wd.findElement(locator).getText();
    }

    String getValue(By locator) {
        if (wd.findElements(locator).size() > 0) {
            return wd.findElement(locator).getAttribute("value");
        } else {
            return "";
        }
    }
}
