package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.BaseTest;

public class ModificationGroupTests extends BaseTest {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isHaveGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            app.getNavigationHelper().goToGroupPage();
        }
        app.getGroupHelper().selectFirstGroups();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test6", "test7"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();

    }
}
