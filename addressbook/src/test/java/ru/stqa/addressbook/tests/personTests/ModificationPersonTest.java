package ru.stqa.addressbook.tests.personTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.List;

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
        List<PersonData> beforePersons = app.getPersonHelper().getPersons();

        app.getPersonHelper().initModificationPerson();
        PersonData modifiedPerson = new PersonData("NewFirstName", "NewMiddleName",
                "NewLastName", "NewNickName", "New   Title", "New  Company",
                "Neww Address", " New Home", "   Mobile   new", "   Work new",
                "", "", "", "", "homepage new", "2018",
                "0000", "address new", "home new", "notes new");
        app.getPersonHelper().fillPersonalData(modifiedPerson);
        app.getPersonHelper().submitModifiedPerson();
        app.getNavigationHelper().goToHomePageOnYellowBlock();
        List<PersonData> afterPersons = app.getPersonHelper().getPersons();

        beforePersons.remove(0);
        beforePersons.add(modifiedPerson);
        app.getPersonHelper().sortBeforeAndAfterPerson(beforePersons, afterPersons);
        Assert.assertEquals(beforePersons, afterPersons);
    }

}
