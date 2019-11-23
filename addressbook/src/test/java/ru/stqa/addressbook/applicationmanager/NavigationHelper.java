package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {
    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void goToCreatePerson() {
        click(By.linkText("add new"));
    }

    public void goToHomePageOnYellowBlock() {
        click(By.linkText("home page"));
    }
}
