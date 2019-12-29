package ru.stqa.addressbook.tests.personTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.tests.BaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InformationPersonTests extends BaseTest {
    @BeforeMethod
    public void checkHavePersonsAndCreate() {
        app.goTo().goToHomePage();
        if (!app.getPersonHelper().isHavePerson()) {
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(new PersonData().withFirstName("F").withLastName("L")
                    .withHomePhone("+7-555-555").withMobilePhone("66 666 66").withWorkPhone("+7(909)-66").withAddress("Address")
                    .withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com").withEmail3("df@df.co"));
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void phoneNumberInPersonTableTest() {
        PersonData contact = app.getPersonHelper().all().iterator().next();
        PersonData contactInfoFromEditForm = app.getPersonHelper().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    @Test
    public void emailInPersonaTableTest() {
        PersonData contact = app.getPersonHelper().all().iterator().next();
        PersonData contactInfoFromEditForm = app.getPersonHelper().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    @Test
    public void addressInPersonaTableTest() {
        PersonData contact = app.getPersonHelper().all().iterator().next();
        PersonData contactInfoFromEditForm = app.getPersonHelper().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(PersonData contact) {
        return merge(Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
    }

    private String mergeEmails(PersonData contact) {
        return merge(Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()));
    }

    private String merge(List<String> fieldsToMerge) {
        return fieldsToMerge
                .stream().filter((s) -> !s.equals(""))
                .map(InformationPersonTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-() ]", "");
    }
}
