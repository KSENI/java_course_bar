package ru.stqa.addressbook.tests.groupTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;
import ru.stqa.addressbook.tests.BaseTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddGroupInPerson extends BaseTest {
    private GroupData groupToAdded = null;

    @BeforeMethod
    public void findPersonWithoutGroupsAndCreateIt() {
        if (checkContacts().size() == 0) { //контактов нет
            createContactAndReturnCreatedContact();
        }
        if (checkGroups().size() == 0) { //групп нет
            groupToAdded = createGroupAndReturnCreatedGroup();
        }
    }

    @Test
    public void addGroupInPerson() {
        Persons contactsBefore = null;

        app.goTo().goToHomePage();

        Persons beforeContacts = app.getDbHelper().persons();
        Groups group = app.getDbHelper().groups();

        PersonData selectedContact = beforeContacts.iterator().next();
        groupToAdded = app.getDbHelper().situatedGroup(group, selectedContact);
        if (groupToAdded == null) {
            app.goTo().goToGroupPage();
            app.getGroupHelper().createGroupAndReturnId(new GroupData().withGroupName("test_new"));
            groupToAdded = app.getDbHelper().getGroupWithMaxIDFromDb();
            contactsBefore = app.getDbHelper().getGroupFromDb(groupToAdded.getGroupId()).getPersons();
            app.goTo().goToHomePage();
        } else {
            contactsBefore = app.getDbHelper().getGroupFromDb(groupToAdded.getGroupId()).getPersons();
        }
        app.getPersonHelper().selectElementById(selectedContact.getId());
        app.getPersonHelper().selectSituatedGroupFromList(groupToAdded);

        Persons contactsAfter = app.getDbHelper().getGroupFromDb(groupToAdded.getGroupId()).getPersons();
        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(selectedContact)));
    }

    public Persons checkContacts() {
        return app.getDbHelper().persons();
    }

    public void createContactAndReturnCreatedContact() {
        app.goTo().goToHomePage();
        PersonData personToAddGroup = new PersonData().withFirstName("New First Name").withLastName("New Last Name")
                .withHomePhone("+7-555-555-new").withMobilePhone("66 666 66-new").withWorkPhone("+7(909)-66-new")
                .withAddress("Address").withEmail1("sdfsf@sfsf.com").withEmail2("sdfsdfsdfadas@sdfsdfsdfafsdf.com")
                .withEmail3("df@df.co").withHomepage("new homepage sdfsdfsdf.com").withMiddleName("New middle Name");
        app.goTo().goToCreatePerson();
        app.getPersonHelper().createPerson(personToAddGroup);
        app.goTo().goToHomePage();
        int maxId = app.getPersonHelper().getMaxId();
        personToAddGroup.withId(maxId);
    }

    public Groups checkGroups() {
        return app.getDbHelper().groups();
    }

    public GroupData createGroupAndReturnCreatedGroup() {
        app.goTo().goToGroupPage();
        groupToAdded = new GroupData().withGroupFooter("footer").withGroupHeader("header").withGroupName("name");
        int idCreatedGroup = app.getGroupHelper().createGroupAndReturnId(groupToAdded);
        groupToAdded.withGroupId(idCreatedGroup);
        app.goTo().goToGroupPage();
        return groupToAdded;
    }
}
