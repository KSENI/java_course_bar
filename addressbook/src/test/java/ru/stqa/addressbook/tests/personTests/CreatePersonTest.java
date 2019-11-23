package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

public class CreatePersonTest extends BaseTest {

    @Test
    public void testCreatePerson() {
        app.getNavigationHelper().goToCreatePerson();
        app.getPersonHelper().fillPersonalData(new PersonData("FirstName", "MiddleName", "LastName", "NickName",
                "Title", "Company", "Address", "Home", "Mobile", "Work",
                "Fax", "emal", "email", "email3", "homepage", "1990",
                "1999", "address", "home", "notes"));
        app.getPersonHelper().submitAddedPerson();
        app.getNavigationHelper().goToHomePage();
    }
}
