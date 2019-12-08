package ru.stqa.addressbook.tests.groupTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.List;

public class CreationGroupTests extends BaseTest {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> beforeGroup = app.getGroupHelper().getGroups();

        GroupData addedGroup = new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(addedGroup);
        app.getNavigationHelper().goToGroupPage();

        List<GroupData> afterGroup = app.getGroupHelper().getGroups();
        beforeGroup.add(addedGroup);
        app.getGroupHelper().sortBeforeAndAfterGroupsList(beforeGroup, afterGroup);
        Assert.assertEquals(beforeGroup, afterGroup);
    }
}
