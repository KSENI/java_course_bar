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
        if (!app.getPersonHelper().isHavePerson()) {
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData().withFirstName("F").withLastName("L"));
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testModificationPerson() {
        Persons beforePersons = app.getPersonHelper().getPersons();
        int id = app.getPersonHelper().selectFirstPersonAndReturnId();
        PersonData modifiedPerson = new PersonData().withFirstName("New First Name").withLastName("New Last Name");

        app.getPersonHelper().initModificationPerson();
        app.getPersonHelper().fillPersonalData(modifiedPerson);
        app.getPersonHelper().submitModifiedPerson();

        app.goTo().goToHomePageOnYellowBlock();
        Persons afterPersons = app.getPersonHelper().getPersons();
        MatcherAssert.assertThat(afterPersons, CoreMatchers.equalTo(beforePersons.without(new PersonData().withId(id))
                .withAdded(modifiedPerson)));
    }

}
