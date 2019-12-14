package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreationGroupTests extends BaseTest {

    @Test
    public void testGroupCreation() {
        app.goTo().goToGroupPage();
        Groups beforeGroup = app.getGroupHelper().getGroups();
        GroupData addedGroup = new GroupData().withGroupName("test1");

        app.getGroupHelper().createGroup(addedGroup);
        app.goTo().goToGroupPage();

        Groups afterGroup = app.getGroupHelper().getGroups();
        assertThat(afterGroup, equalTo(beforeGroup.withAdded(addedGroup)));
    }
}
