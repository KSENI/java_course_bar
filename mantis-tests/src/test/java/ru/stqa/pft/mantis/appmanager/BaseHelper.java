package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
    protected WebDriver wd;
    protected ApplicationManager app;

    public BaseHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
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
}
