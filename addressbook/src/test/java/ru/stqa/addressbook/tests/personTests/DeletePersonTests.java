package ru.stqa.addressbook.tests.personTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.List;

public class DeletePersonTests extends BaseTest {
    @Test
    public void testDeletePerson() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getPersonHelper().isHavePerson()) {
            app.getNavigationHelper().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData("First Name", null, "LastName", null,
                    null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null));
            app.getNavigationHelper().goToHomePage();
        }
        List<PersonData> beforePersons = app.getPersonHelper().getPersons();
        app.getPersonHelper().selectFirstPerson();
        app.getPersonHelper().deleteSelectedPerson();

        app.getNavigationHelper().goToHomePage();
        List<PersonData> afterPersons = app.getPersonHelper().getPersons();
        beforePersons.remove(0);
        app.getPersonHelper().sortBeforeAndAfterPerson(beforePersons, afterPersons);
        Assert.assertEquals(beforePersons, afterPersons);
    }
}
