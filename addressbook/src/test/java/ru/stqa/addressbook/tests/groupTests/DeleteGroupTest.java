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
        if (app.getDbHelper().groups().size() == 0) {
            app.getGroupHelper().createGroupAndReturnId(new GroupData().withGroupName("test1"));
            app.goTo().goToGroupPage();
        }
    }
    @Test
    public void testDeleteGroup() {
        Groups beforeGroups = app.getDbHelper().groups();
        GroupData deletedGroup = ((GroupData) beforeGroups.toArray()[0]);

        app.goTo().goToGroupPage();
        app.getGroupHelper().deleteGroup(deletedGroup);
        app.goTo().goToGroupPage();

        Groups afterGroups = app.getDbHelper().groups();
        assertThat(afterGroups, equalTo(beforeGroups.without(deletedGroup)));
    }
}
