package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends BaseTest {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectFirstGroups();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
