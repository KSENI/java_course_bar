package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.tests.BaseTest;

public class DeletePersonTests extends BaseTest {
    @Test
    public void testDeletePerson() {
        app.getNavigationHelper().goToHomePage();
        app.getPersonHelper().selectFirstPerson();
        app.getPersonHelper().deleteSelectedPerson();
    }
}
