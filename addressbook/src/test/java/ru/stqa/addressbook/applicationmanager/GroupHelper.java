package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper {

    GroupHelper(WebDriver wd) {
        super(wd);
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public int selectFirstGroupAndReturnId() {
        By locator = By.name("selected[]");
        WebElement element = wd.findElement(locator);
        int id = Integer.parseInt(element.getAttribute("value"));
        click(locator);
        return id;
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModificationAndReturnId() {
        click(By.name("update"));
    }

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(new GroupData()
                .withGroupName(groupData.getGroupName())
                .withGroupHeader(groupData.getGroupHeader())
                .withGroupFooter(groupData.getGroupFooter()));
        submitGroupCreation();
    }

    public boolean isHaveGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public Groups getGroups() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            groups.add(new GroupData().withGroupName(element.getText()));
        }
        return groups;
    }
}
