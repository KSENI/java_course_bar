package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletePersonTests extends BaseTest {
    @BeforeMethod
    public void checkHavePersonsAndCreate() {
        app.goTo().goToHomePage();
        if (!app.getPersonHelper().isHavePerson()) {
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData().withFirstName("First Name").withLastName("Last Name")
                    .withHomePhone("+7-555-555").withMobilePhone("66 666 66").withWorkPhone("+7(909)-66").withAddress("Address")
                    .withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com").withEmail3("df@df.co"));
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testDeletePerson() {
        Persons beforePersons = app.getPersonHelper().getPersons();
        PersonData deletedPerson = ((PersonData) beforePersons.toArray()[0]);

        app.getPersonHelper().deleteSelectedPerson(deletedPerson);

        app.goTo().goToHomePage();
        Persons afterPersons = app.getPersonHelper().getPersons();
        assertThat(afterPersons, equalTo(beforePersons.without(deletedPerson)));
    }
}
