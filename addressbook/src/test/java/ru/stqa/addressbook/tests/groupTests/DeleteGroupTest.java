package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.tests.BaseTest;

public class DeleteGroupTest extends BaseTest {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectFirstGroups();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
