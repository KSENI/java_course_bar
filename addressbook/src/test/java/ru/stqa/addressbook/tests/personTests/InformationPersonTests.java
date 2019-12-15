package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InformationPersonTests extends BaseTest {
    @BeforeMethod
    public void checkHavePersonsAndCreate() {
        app.goTo().goToHomePage();
        if (!app.getPersonHelper().isHavePerson()) {
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData().withFirstName("F").withLastName("L")
                    .withHomePhone("+7-555-555").withMobilePhone("66 666 66").withWorkPhone("+7(909)-66").withAddress("Address"));
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void phoneNumberInPersonTableTest() {
        String allPhonesInTable = app.getPersonHelper().getPhoneNumbersInTableForFirstPerson();
        app.getPersonHelper().initModificationPerson();
        String allPhonesInPersonPage = app.getPersonHelper().getPhoneNumbersInPersonPageForFirstPerson();

        allPhonesInTable = allPhonesInTable.replace("\n", "");
        allPhonesInPersonPage = allPhonesInPersonPage.replaceAll("[\\s-()]", "");
        assertThat(allPhonesInTable, equalTo(allPhonesInPersonPage));
    }

    @Test
    public void addressInPersonTableTest() {
        String addressInTable = app.getPersonHelper().getAddressInTableForFirstPerson();
        app.getPersonHelper().initModificationPerson();
        String addressInPersonPage = app.getPersonHelper().getAddressInPersonPageForFirstPerson();

        assertThat(addressInTable, equalTo(addressInPersonPage));
    }

    @Test
    public void emailsInPersonTableTest() {
        String emailsInTable = app.getPersonHelper().getEmailsInTableForFirstPerson();
        app.getPersonHelper().initModificationPerson();
        String emailsInPersonPage = app.getPersonHelper().getEmailsInPersonPageForFirstPerson();

        emailsInTable = emailsInTable.replace("\n", "");
        assertThat(emailsInTable, equalTo(emailsInPersonPage));
    }
}
