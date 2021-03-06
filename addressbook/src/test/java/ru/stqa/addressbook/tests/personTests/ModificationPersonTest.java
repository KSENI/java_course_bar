package ru.stqa.addressbook.tests.personTests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;


public class ModificationPersonTest extends BaseTest {
    @BeforeMethod
    public void checkHavePersonsAndCreate() {
        app.goTo().goToHomePage();
        if (app.getDbHelper().persons().size() == 0) {
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData().withFirstName("F").withLastName("L")
                    .withHomePhone("+7-555-555").withMobilePhone("66 666 66").withWorkPhone("+7(909)-66").withAddress("Address"));
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testModificationPerson() {
        Persons beforePersons = app.getDbHelper().persons();
        PersonData oldPerson = (PersonData) beforePersons.toArray()[0];
        int oldId = oldPerson.getId();
        PersonData newPerson = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");

        app.getPersonHelper().initModificationPerson(oldPerson);
        app.getPersonHelper().fillPersonalData(newPerson.withId(oldId));
        app.getPersonHelper().submitModifiedPerson();

        app.goTo().goToHomePageOnYellowBlock();
        Persons afterPersons = app.getDbHelper().persons();
        MatcherAssert.assertThat(afterPersons, CoreMatchers.equalTo(beforePersons.without(oldPerson)
                .withAdded(newPerson)));
    }

}
