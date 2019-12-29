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

    public void deleteGroup(GroupData groupData) {
        click(By.cssSelector("input[type='checkbox'][value='" + groupData.getGroupId() + "']"));
        click(By.name("delete"));
    }

    public void initGroupModification(GroupData groupData) {
        click(By.cssSelector("input[type='checkbox'][value='" + groupData.getGroupId() + "']"));
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
            int id = Integer.parseInt(element.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value"));
            groups.add(new GroupData().withGroupName(element.getText()).withGroupId(id));
        }
        return groups;
    }
}
