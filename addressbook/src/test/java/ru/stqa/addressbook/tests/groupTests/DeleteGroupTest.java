package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.BaseTest;

public class DeleteGroupTest extends BaseTest {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isHaveGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            app.getNavigationHelper().goToGroupPage();
        }
        app.getGroupHelper().selectFirstGroups();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
