package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddGroupInPerson extends BaseTest {
    private PersonData personToAddGroup = null;

    @BeforeMethod
    public void findPersonWithoutGroupsAndCreateIt() {
        Persons allPerson = app.getDbHelper().persons();
        if (allPerson.size() == 0) { //контактов вообще нет
            app.goTo().goToHomePage();
            personToAddGroup = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                    .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                    .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                    .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(personToAddGroup);
            app.goTo().goToHomePage();
            int maxId = app.getPersonHelper().getMaxId();
            personToAddGroup.withId(maxId);
        } else { //если какие-то контакты есть проверка есть ли в них контакт без группы
            for (PersonData person : allPerson) {
                if (person.getGroups().size() == 0) {
                    personToAddGroup = person;
                    return;
                }
            }
            if (personToAddGroup == null) { // если контакта без группы нет создать контакт
                app.goTo().goToHomePage();
                int maxId = app.getPersonHelper().getMaxId();
                personToAddGroup = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                        .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                        .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                        .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");
                app.goTo().goToCreatePerson();
                app.getPersonHelper().createPerson(personToAddGroup.withId(maxId + 1));
                app.goTo().goToHomePage();
            }
        }

    }

    @Test
    public void addGroupInPerson() {
        app.getPersonHelper().addGroupToPerson(personToAddGroup);

        Groups afterGroups = new Groups();
        Persons afterPersons = app.getDbHelper().persons();
        for (PersonData person : afterPersons) {
            if (person.equals(personToAddGroup))
                afterGroups = person.getGroups();
        }
        assertThat(afterGroups.size(), equalTo(1));
    }
}
