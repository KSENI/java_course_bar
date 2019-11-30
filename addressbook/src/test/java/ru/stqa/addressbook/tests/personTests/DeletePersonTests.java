package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

public class DeletePersonTests extends BaseTest {
    @Test
    public void testDeletePerson() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getPersonHelper().isHavePerson()) {
            app.getNavigationHelper().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData("First Name", null, null, null,
                    null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null));
            app.getNavigationHelper().goToHomePage();
        }
        app.getPersonHelper().selectFirstPerson();
        app.getPersonHelper().deleteSelectedPerson();
    }
}
