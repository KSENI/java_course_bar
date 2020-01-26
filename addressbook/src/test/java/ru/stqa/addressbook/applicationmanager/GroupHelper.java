package ru.stqa.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Collections;
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

    public int createGroupAndReturnId(GroupData groupData) {
        int idCreatedGroup = getMaximumId() + 1;
        initGroupCreation();
        fillGroupForm(new GroupData()
                .withGroupId(idCreatedGroup)
                .withGroupName(groupData.getGroupName())
                .withGroupHeader(groupData.getGroupHeader())
                .withGroupFooter(groupData.getGroupFooter()));
        submitGroupCreation();
        return idCreatedGroup;
    }

    public void createGroupAnd(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(new GroupData()
                .withGroupName(groupData.getGroupName())
                .withGroupHeader(groupData.getGroupHeader())
                .withGroupFooter(groupData.getGroupFooter()));
        submitGroupCreation();
    }

    public int getMaximumId() {
        List<WebElement> allGroups = wd.findElements(By.cssSelector("input[type='checkbox']"));
        List<Integer> allIds = new ArrayList<>();
        for (WebElement group : allGroups) {
            allIds.add(Integer.parseInt(group.getAttribute("value")));
        }
        return Collections.max(allIds);
    }

    public boolean isHaveGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public Groups getGroupsInTable() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value"));
            groups.add(new GroupData().withGroupName(element.getText()).withGroupId(id));
        }
        return groups;
    }
/*
    public Groups getAllGroupInformation() {
        Groups groups = new Groups();
        List<WebElement> allGroupInTable = wd.findElements(By.cssSelector("span.group"));
        for (WebElement groupInTable: allGroupInTable) {
            int id = Integer.parseInt(
                    groupInTable.findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value"));
            groupInTable.findElement(By.cssSelector("input[type='checkbox']")).click();
            click(By.name("edit"));
            String name = wd.findElement(By.name("group_name")).getAttribute("value");
            String header = wd.findElement(By.name("group_header")).getAttribute("value");
            String footer = wd.findElement(By.name("group_footer")).getAttribute("value");
            groups.add(new GroupData().withGroupId(id).withGroupName(name).withGroupHeader(header).withGroupFooter(footer));
            wd.findElement(By.xpath("//a[contains(text(),'groups')]")).click();
        }
        return groups;
    } */
}
