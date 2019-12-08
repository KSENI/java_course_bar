package ru.stqa.addressbook.tests.personTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.List;

public class CreatePersonTest extends BaseTest {

    @Test
    public void testCreatePerson() {
        app.getNavigationHelper().goToHomePage();
        List<PersonData> beforePersons = app.getPersonHelper().getPersons();
        app.getNavigationHelper().goToCreatePerson();
        PersonData addedPerson = new PersonData("FirstName", "MiddleName", "LastName", "NickName",
                "Title", "Company", "Address", "Home", "Mobile", "Work",
                "Fax", "emal", "email", "email3", "homepage", "1990",
                "1999", "address", "home", "notes");
        app.getPersonHelper().createPerson(addedPerson);
        app.getNavigationHelper().goToHomePage();
        List<PersonData> afterPersons = app.getPersonHelper().getPersons();

        beforePersons.add(addedPerson);
        app.getPersonHelper().sortBeforeAndAfterPerson(beforePersons, afterPersons);
        Assert.assertEquals(beforePersons, afterPersons);
    }
}
