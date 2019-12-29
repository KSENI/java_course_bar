package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteGroupTest extends BaseTest {

    @BeforeMethod
    public void checkHaveGroupAndCreateGroup() {
        app.goTo().goToGroupPage();
        if (!app.getGroupHelper().isHaveGroup()) {
            app.getGroupHelper().createGroup(new GroupData().withGroupName("test1"));
            app.goTo().goToGroupPage();
        }
    }
    @Test
    public void testDeleteGroup() {
        Groups beforeGroups = app.getGroupHelper().getGroups();

        GroupData deletedGroup = ((GroupData) beforeGroups.toArray()[0]);
        app.getGroupHelper().deleteGroup(deletedGroup);
        app.goTo().goToGroupPage();

        Groups afterGroups = app.getGroupHelper().getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.without(deletedGroup)));
    }
}
