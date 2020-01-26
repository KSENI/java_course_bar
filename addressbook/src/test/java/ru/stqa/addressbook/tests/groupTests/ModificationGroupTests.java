package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationGroupTests extends BaseTest {
    @BeforeMethod
    public void checkHaveGroupAndCreateGroup() {
        if (app.getDbHelper().groups().size() == 0) {
            app.goTo().goToGroupPage();
            app.getGroupHelper().createGroupAnd(new GroupData().withGroupName("test2"));
        }
    }
    @Test
    public void testGroupModification() {
        app.goTo().goToGroupPage();
        Groups beforeGroups = app.getDbHelper().groups();
        GroupData oldGroup = (GroupData) beforeGroups.toArray()[0];
        int id = oldGroup.getGroupId();
        app.getGroupHelper().initGroupModification(oldGroup);

        long uniqGuid = new Date().getTime();
        GroupData modifiedGroup = new GroupData().withGroupName("new group name")
                .withGroupFooter("new group footer" + uniqGuid).withGroupHeader("new group header").withGroupId(id);
        app.getGroupHelper().fillGroupForm(modifiedGroup);
        app.getGroupHelper().submitGroupModificationAndReturnId();

        Groups afterGroups = app.getDbHelper().groups();
        assertThat(afterGroups, equalTo(beforeGroups
                .withAdded(modifiedGroup).without(oldGroup)));
    }
}
