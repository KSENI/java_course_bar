package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonDeleteInGroups extends BaseTest {
    private PersonData personToDeleteGroup;

    @BeforeMethod
    public void checkPersonHaveGroup() {
        Persons allPerson = app.getDbHelper().persons();
        if (allPerson.size() == 0) { //контактов вообще нет
            app.goTo().goToHomePage();
            personToDeleteGroup = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                    .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                    .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                    .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");
            app.goTo().goToCreatePerson();
            app.getPersonHelper().createPerson(personToDeleteGroup);
            app.goTo().goToHomePage();
            int maxId = app.getPersonHelper().getMaxId();
            personToDeleteGroup.withId(maxId);
            app.getPersonHelper().addGroupToPerson(personToDeleteGroup);
            app.goTo().goToHomePage();
        } else {
            for (PersonData person : allPerson) {
                if (person.getGroups().size() == 1) { //если есть контакт с одной группой выбрать его для удаления
                    personToDeleteGroup = person;
                    return;
                }
            }
            if (personToDeleteGroup == null) { // если контакта с групой нет создать контакт и добавить ему группу
                app.goTo().goToHomePage();
                int maxId = app.getPersonHelper().getMaxId();
                personToDeleteGroup = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                        .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                        .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                        .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");
                app.goTo().goToCreatePerson();
                app.getPersonHelper().createPerson(personToDeleteGroup.withId(maxId + 1));
                app.goTo().goToHomePage();
                app.getPersonHelper().addGroupToPerson(personToDeleteGroup);
            }
        }
    }

    @Test
    public void deleteGroupsInPerson() {
        Groups beforeGroups = new Groups();
        Persons beforePersons = app.getDbHelper().persons();
        for (PersonData person : beforePersons) {
            if (person.equals(personToDeleteGroup))
                beforeGroups = person.getGroups();
        }
        personToDeleteGroup.withGroup(beforeGroups);

        app.goTo().goToHomePage();
        app.getPersonHelper().deleteGroup(personToDeleteGroup);

        Groups afterGroups = new Groups();
        Persons afterPersons = app.getDbHelper().persons();
        for (PersonData person : afterPersons) {
            if (person.equals(personToDeleteGroup))
                afterGroups = person.getGroups();
        }
        assertThat(afterGroups.size(), equalTo(0));
    }
}
