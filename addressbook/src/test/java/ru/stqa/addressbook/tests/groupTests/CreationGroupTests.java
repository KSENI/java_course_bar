package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.BaseTest;

public class CreationGroupTests extends BaseTest {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().returnToGroupPage();
    }

}
