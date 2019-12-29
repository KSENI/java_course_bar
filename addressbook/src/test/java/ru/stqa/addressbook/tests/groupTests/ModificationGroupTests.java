package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationGroupTests extends BaseTest {
    @BeforeMethod
    public void checkHaveGroupAndCreateGroup() {
        app.goTo().goToGroupPage();
        if (!app.getGroupHelper().isHaveGroup()) {
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test1"));
            app.goTo().goToGroupPage();
        }
    }
    @Test
    public void testGroupModification() {
        Groups beforeGroups = app.getGroupHelper().getGroups();

        GroupData oldGroup = (GroupData) beforeGroups.toArray()[0];
        int id = oldGroup.getGroupId();
        app.getGroupHelper().initGroupModification(oldGroup);
        GroupData modifiedGroup = new GroupData().withGroupName("test5").withGroupId(id);
        app.getGroupHelper().fillGroupForm(modifiedGroup);
        app.getGroupHelper().submitGroupModificationAndReturnId();
        app.goTo().goToGroupPage();

        Groups afterGroups = app.getGroupHelper().getGroups();
        assertThat(afterGroups, equalTo(beforeGroups
                .withAdded(modifiedGroup).without(oldGroup)));
    }
}
