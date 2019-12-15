package ru.stqa.addressbook.tests.personTests;


import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePersonTest extends BaseTest {

    @Test
    public void testCreatePerson() {
        app.goTo().goToHomePage();
        Persons beforePersons = app.getPersonHelper().getPersons();
        app.goTo().goToCreatePerson();
        PersonData addedPerson = new PersonData().withFirstName("FirstName").withLastName("LastName")
                .withHomePhone("+7-555-555").withMobilePhone("66 666 66").withWorkPhone("+7(909)-66").withAddress("Address");

        app.getPersonHelper().createPerson(addedPerson);
        app.goTo().goToHomePage();

        Persons afterPersons = app.getPersonHelper().getPersons();
        assertThat(afterPersons, equalTo(beforePersons.withAdded(addedPerson)));
    }
}
