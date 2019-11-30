package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

public class ModificationPersonTest extends BaseTest {
    @Test
    public void testModificationPerson() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getPersonHelper().isHavePerson()) {
            app.getNavigationHelper().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData("First Name", null, null, null,
                    null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null));
            app.getNavigationHelper().goToHomePage();
        }
        app.getPersonHelper().initModificationPerson();
        app.getPersonHelper().fillPersonalData(new PersonData("NewFirstName", "NewMiddleName",
                "NewLastName", "NewNickName", "New   Title", "New  Company",
                "Neww Address", " New Home", "   Mobile   new", "   Work new",
                "", "", "", "", "homepage new", "2018",
                "0000", "address new", "home new", "notes new"));
        app.getPersonHelper().submitModifiedPerson();
        app.getNavigationHelper().goToHomePageOnYellowBlock();
    }

}
