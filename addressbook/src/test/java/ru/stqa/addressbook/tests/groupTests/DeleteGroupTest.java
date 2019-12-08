package ru.stqa.addressbook.tests.groupTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.List;

public class DeleteGroupTest extends BaseTest {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isHaveGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            app.getNavigationHelper().goToGroupPage();
        }
        List<GroupData> beforeGroups = app.getGroupHelper().getGroups();

        app.getGroupHelper().selectFirstGroups();
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().goToGroupPage();

        List<GroupData> afterGroups = app.getGroupHelper().getGroups();
        beforeGroups.remove(0);
        app.getGroupHelper().sortBeforeAndAfterGroupsList(beforeGroups, afterGroups);
        Assert.assertEquals(beforeGroups, afterGroups);
    }
}
